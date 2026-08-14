package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.tools.SdkVersion;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManagerBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpVersion;
import org.apache.hc.core5.http2.HttpVersionPolicy;
import org.apache.hc.core5.util.Timeout;

final class Http2JsonTransport {

  private static final int CONNECT_TIMEOUT_SECONDS = 15;
  private static final int REQUEST_TIMEOUT_SECONDS = 30;
  private static final CloseableHttpAsyncClient HTTP_CLIENT = createClient();

  private Http2JsonTransport() {}

  static String post(String gatewayUrl, String path, String sessionId, String requestBody)
      throws AlipayApiException {
    URI requestUri = buildRequestUri(gatewayUrl, path);
    SimpleHttpRequest request = SimpleHttpRequest.create("POST", requestUri);
    request.setHeader("X-Session-Id", sessionId);
    request.setHeader("Accept", "application/json");
    request.setHeader("User-Agent", SdkVersion.getUserAgent());
    request.setBody(
        requestBody, ContentType.create("application/json", StandardCharsets.UTF_8));

    Future<SimpleHttpResponse> responseFuture = HTTP_CLIENT.execute(request, null);
    SimpleHttpResponse response;
    try {
      response = responseFuture.get(REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      responseFuture.cancel(true);
      Thread.currentThread().interrupt();
      throw new AlipayApiException("HTTP/2 request was interrupted.", e);
    } catch (ExecutionException e) {
      Throwable cause = e.getCause() == null ? e : e.getCause();
      throw new AlipayApiException("HTTP/2 request failed.", cause);
    } catch (TimeoutException e) {
      responseFuture.cancel(true);
      throw new AlipayApiException("HTTP/2 request timed out.", e);
    }

    if (!HttpVersion.HTTP_2.equals(response.getVersion())) {
      throw new AlipayApiException(
          "This API requires HTTP/2, but negotiated protocol was " + response.getVersion() + ".");
    }

    String responseBody = response.getBodyText();
    if (response.getCode() != 200) {
      throw new AlipayApiException(
          "Response data error, HTTP status=" + response.getCode() + ", rspBody:" + responseBody);
    }
    return responseBody == null ? "" : responseBody;
  }

  private static CloseableHttpAsyncClient createClient() {
    CloseableHttpAsyncClient client =
        HttpAsyncClients.custom()
            .setConnectionManager(
                PoolingAsyncClientConnectionManagerBuilder.create()
                    .useSystemProperties()
                    .setDefaultConnectionConfig(
                        ConnectionConfig.custom()
                            .setConnectTimeout(Timeout.ofSeconds(CONNECT_TIMEOUT_SECONDS))
                            .build())
                    .build())
            .setVersionPolicy(HttpVersionPolicy.FORCE_HTTP_2)
            .useSystemProperties()
            .disableAutomaticRetries()
            .disableContentCompression()
            .disableRedirectHandling()
            .disableCookieManagement()
            .setThreadFactory(
                runnable -> {
                  Thread thread = new Thread(runnable, "antom-session-http2");
                  thread.setDaemon(true);
                  return thread;
                })
            .build();
    client.start();
    return client;
  }

  private static URI buildRequestUri(String gatewayUrl, String path) throws AlipayApiException {
    if (StringUtils.isBlank(gatewayUrl)) {
      throw new AlipayApiException("gatewayUrl can't be null or blank.");
    }
    if (StringUtils.isBlank(path) || !path.startsWith("/")) {
      throw new AlipayApiException("path must start with /.");
    }

    try {
      URI gateway = new URI(gatewayUrl.trim());
      if (!"https".equalsIgnoreCase(gateway.getScheme())
          || gateway.getHost() == null
          || gateway.getUserInfo() != null
          || gateway.getQuery() != null
          || gateway.getFragment() != null
          || (StringUtils.isNotEmpty(gateway.getPath()) && !"/".equals(gateway.getPath()))) {
        throw new AlipayApiException(
            "gatewayUrl must be an HTTPS origin without path, query, fragment, or user info.");
      }
      return new URI(
          "https", null, gateway.getHost(), gateway.getPort(), path, null, null);
    } catch (URISyntaxException e) {
      throw new AlipayApiException("gatewayUrl is invalid.", e);
    }
  }
}
