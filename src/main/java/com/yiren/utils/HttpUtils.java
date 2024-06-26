package com.yiren.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
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

/**
 * desc:
 *
 * @author Viper Thanks
 * @since 25/6/2024
 */
public class HttpUtils {


    private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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


    public static CloseableHttpClient getHttpClient() {
        return HttpClientConnectionPool.getHttpClient();
    }

    public static String doPost(String uri, String body) throws HttpException {
        try {
            return doPost(new URI(uri), body);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String doPost(URI uri, Map<String, Object> body) throws HttpException {
        return doPost(uri, JSONUtils.toJsonString(body));
    }

    public static String doPost(URI uri, String body) throws HttpException {
        final HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        httpPost.addHeader(HTTP.CONTENT_ENCODING, StandardCharsets.UTF_8.name());
        try {
            // 使用 JSON 库序列化 body 参数
            StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            try (CloseableHttpResponse response = getHttpClient().execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());
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

    public static String doGet(String uri) throws URISyntaxException {
        return doGet(new URI(uri));
    }

    public static String doGet(URI url) {
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = getHttpClient().execute(httpGet)) {
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            LOG.error("get url :: {} error", url, e);
            return null;
        }
    }
}
