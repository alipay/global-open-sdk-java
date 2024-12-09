package com.alipay.global.api.net;

import com.alipay.global.api.model.constants.EndPointConstants;
import com.alipay.global.api.tools.Constants;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Getter
public class HttpClientPoolUtil {


    /**
     * 从连接管理器请求连接时使用的超时时间（单位/毫秒）
     * 默认值： -1，为无限超时。
     */
    private final static int connectionRequestTimeout = 2000;

    /**
     * 确定建立连接之前的超时时间（单位/毫秒）
     * 默认值： -1，为无限超时。
     */
    private final static int connectTimeout = 1000;

    /**
     *  待数据的超时（单位/毫秒）
     * 默认值： -1，为无限超时。
     */
    private final static int socketTimeout = 3000;

    /**
     * 连接池最大连接数
     * 默认值：20
     */
    private final static int maxTotal = 1;

    /**
     * 每个路由最大连接数
     * 默认值：2
     */
    private final static int maxPreRoute = 10;

    /**
     * 某路由最大连接数
     * 默认值：2
     */
    private final static int maxRoute = 10;

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


    private  CloseableHttpClient httpClient;


    public HttpClientPoolUtil() {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxPreRoute);
        // 设置某个路由最大连接数
        connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost(new HttpHost(EndPointConstants.SG))), maxRoute);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .build();


        //2.创建httpclient对象
        httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setRetryHandler(new DefaultHttpRequestRetryHandler())
                .setDefaultRequestConfig(requestConfig)
                .build();


        // 服务端假设关闭了连接，对客户端是不透明的，HttpClient为了缓解这一问题，在某个连接使用前会检测这个连接是否过时，如果过时则连接失效，但是这种做法会为每个请求
        // 增加一定额外开销，因此有一个定时任务专门回收长时间不活动而被判定为失效的连接，可以某种程度上解决这个问题
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    // 关闭失效连接并从连接池中移除
                    connectionManager.closeExpiredConnections();
                    // 关闭30秒钟内不活动的连接并从连接池中移除，空闲时间从交还给连接管理器时开始
                    connectionManager.closeIdleConnections(20, TimeUnit.SECONDS);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }, 0 , 1000 * 5);

       HttpClientRpc.doPost(httpClient, EndPointConstants.SG, null, "");
    }


    public void close() throws IOException {
        httpClient.close();
    }

}
