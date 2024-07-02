package com.yiren.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

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

    /**
     * 做post请求
     * @param uri 请求的url
     * @param body 请求体
     * @return 请求响应体
     * @throws HttpException http异常
     */
    public static String doPost(String uri, String body) throws HttpException {
        try {
            return doPost(new URI(uri), body);
        } catch (URISyntaxException e) {
            LOG.error("url 格式错误，请检查格式。");
            throw new RuntimeException(e);
        }
    }

    public static String doPost(URI uri, Map<String, Object> body) throws HttpException {
        return doPost(uri, JSONUtils.toJsonString(body));
    }

    public static String doPost(URI uri, String body) throws HttpException {
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
            throw new HttpException("Error sending HTTP POST request", e);
        }
    }

    public static String doGet(String uri) throws HttpException {
        try {
            return doGet(new URI(uri));
        } catch (URISyntaxException e) {
            LOG.error("url 格式错误，请检查格式。");
            throw new RuntimeException(e);
        }
    }

    public static String doGet(URI url) throws HttpException {
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = getHttpClient().execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());
            if (statusCode >= 200 && statusCode < 300) { // 检查状态码是否成功
                return responseBody;
            } else {
                throw new HttpException("HTTP POST request failed with status code: " + statusCode);
            }
        } catch (IOException e) {
            throw new HttpException("Error sending HTTP POST request", e);
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
