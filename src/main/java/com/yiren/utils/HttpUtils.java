package com.yiren.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * desc: http 工具类
 *
 * @author Viper Thanks
 * @since 25/6/2024
 */
public final class HttpUtils {
    private HttpUtils(){}

    private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * http连接池实现
     */
    private static final class HttpClientConnectionPool {
        private static final PoolingHttpClientConnectionManager CONNECTION_MANAGER = new PoolingHttpClientConnectionManager();
        private static final CloseableHttpClient HTTP_CLIENT;

        static {
            CONNECTION_MANAGER.setMaxTotal(200); // 最大连接数
            CONNECTION_MANAGER.setDefaultMaxPerRoute(20); // 每个路由默认最大连接数

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .setConnectionRequestTimeout(1000)
                    .build();

            HTTP_CLIENT = HttpClients.custom()
                    .setConnectionManager(CONNECTION_MANAGER)
                    .setDefaultRequestConfig(requestConfig)
                    .build();
        }

        public static CloseableHttpClient getHttpClient() {
            return HTTP_CLIENT;
        }
    }

    /**
     * 获取http客户端
     * @return http客户端，基于连接池
     */
    public static CloseableHttpClient getHttpClient() {
        return HttpClientConnectionPool.getHttpClient();
    }


    public static String doPost(String uri, Map<String, Object> body) throws HttpException {
        return doPost(uri, JSONUtils.toJsonString(body));
    }

    /**
     * 基于apache http客户端的post请求实现
     * @param uri
     * @param body
     * @return
     * @throws HttpException
     */
    public static String doPost(String uri, String body) throws HttpException {
        checkNotNull(uri, "uri不能为空");
        checkState(StringUtils.isNotBlank(body), "body不能为空");
        final HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        httpPost.addHeader(HTTP.CONTENT_ENCODING, StandardCharsets.UTF_8.name());
        try {
            // 使用 JSON 库序列化 body 参数
            StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            try (CloseableHttpResponse response = getHttpClient().execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                if (statusCode >= 200 && statusCode < 300) { // 检查状态码是否成功
                    return responseBody;
                } else {
                    throw new HttpException("HTTP POST request failed with status code: " + statusCode);
                }
            }
        } catch (IOException e) {
            //释放链接，由于CloseableHttpResponse已经在tru with resource代码块里，所以只需要让httpPost释放链接就行了
            httpPost.releaseConnection();
            throw new HttpException("Error sending HTTP POST request", e);
        }
    }

    public static String doGet(String uri) throws HttpException {
        return doGet(uri, Collections.emptyMap());
    }

    public static String doGet(String url, Map<String, Object> params) throws HttpException {
        checkNotNull(url, "uri is null! ");
        checkNotNull(params, "params is null! ");
        HttpGet httpGet = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            params.forEach((k, v) -> uriBuilder.addParameter(k, String.valueOf(v)));
            httpGet = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            LOG.error("url {}格式错误，请检查格式。", url);
            throw new HttpException("URL 格式错误，请检查格式");
        }
        try (CloseableHttpResponse response = getHttpClient().execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            if (statusCode >= 200 && statusCode < 300) { // 检查状态码是否成功
                return responseBody;
            } else {
                throw new HttpException("HTTP GET request failed with status code: " + statusCode);
            }
        } catch (IOException e) {
            httpGet.releaseConnection();
            throw new HttpException("Error sending HTTP GET request", e);
        }
    }

    public static HttpRequestBuilder newBuilder() {
        return new HttpRequestBuilder();
    }

    public static class HttpRequestBuilder{
        private String uri;
        private String body;
        private Map<String, Object> header;

        public HttpRequestBuilder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public HttpRequestBuilder body(String body) {
            this.body = body;
            return this;
        }

        public HttpRequestBuilder header(Map<String, Object> header) {
            this.header = header;
            return this;
        }

        public String doPost() throws HttpException {
            checkState(StringUtils.isNoneBlank(uri, body),"post请求url和body不能为blank");
            return HttpUtils.doPost(uri, body);
        }

        public String doGet() throws HttpException {
            checkState(StringUtils.isNotBlank(uri));
            return HttpUtils.doGet(uri);
        }

    }
}
