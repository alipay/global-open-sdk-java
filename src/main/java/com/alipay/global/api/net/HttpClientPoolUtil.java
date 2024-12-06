package com.alipay.global.api.net;

import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.tools.Constants;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Getter
public class HttpClientPoolUtil {


    /**
     * 从连接管理器请求连接时使用的超时时间（单位/毫秒）
     * 默认值： -1，为无限超时。
     */
    private final static int connectionRequestTimeout = 10000;

    /**
     * 确定建立连接之前的超时时间（单位/毫秒）
     * 默认值： -1，为无限超时。
     */
    private final static int connectTimeout = 10000;

    /**
     *  待数据的超时（单位/毫秒）
     * 默认值： -1，为无限超时。
     */
    private final static int socketTimeout = 10000;

    /**
     * 连接池最大连接数
     * 默认值：20
     */
    private final static int maxTotal = 50;

    /**
     * 每个路由最大连接数
     * 默认值：2
     */
    private final static int maxPreRoute = 5;

    /**
     * 连接存活时长：秒
     */
    private final static long connectionTimeToLive = 60;

    /**
     * 重试尝试最大次数
     * 默认为3
     */
    private final static int retryCount = 10;

    /**
     * 非幂等请求是否可以重试
     * 默认不开启
     */
    private final static boolean requestSentRetryEnabled = false;


    private final CloseableHttpClient httpClient;


    public HttpClientPoolUtil() {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(2,
                TimeUnit.SECONDS);
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxPreRoute);
        // 设置某个路由最大连接数
        connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost(EndPointConstants.SG)), 20);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .build();


        //2.创建httpclient对象
        httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount, requestSentRetryEnabled))
                .evictIdleConnections(connectionTimeToLive, TimeUnit.SECONDS)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }


    public void close() throws IOException {
        httpClient.close();
    }

}
