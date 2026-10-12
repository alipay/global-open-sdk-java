package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.AlipayResponse;
import com.alipay.global.api.tools.JsonUtil;
import com.alipay.global.api.tools.SdkVersion;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.util.Timeout;

/** Independent API Key client. Request models and business responses are shared with RSA. */
public class ApiKeyAlipayClient implements AlipayClient {
  private final ApiKeyAuth auth;
  private final int connectTimeoutMillis;
  private final int readTimeoutMillis;

  /** Creates a client using an HTTPS regional gateway and the complete API Key. */
  public ApiKeyAlipayClient(String gatewayUrl, String apiKey) {
    this(new Builder(gatewayUrl, apiKey));
  }

  private ApiKeyAlipayClient(Builder builder) {
    auth = new ApiKeyAuth(builder.gatewayUrl, builder.apiKey);
    if (builder.connectTimeoutMillis <= 0 || builder.readTimeoutMillis <= 0) {
      throw new IllegalArgumentException("Timeouts must be positive");
    }
    connectTimeoutMillis = builder.connectTimeoutMillis;
    readTimeoutMillis = builder.readTimeoutMillis;
  }

  /** Creates a builder for optional transport timeouts. */
  public static Builder builder(String gatewayUrl, String apiKey) {
    return new Builder(gatewayUrl, apiKey);
  }

  @Override
  public <T extends AlipayResponse> T execute(AlipayRequest<T> request) throws AlipayApiException {
    return executeWithHeaders(request, null);
  }

  /** Executes a JSON request, or delegates uploadEvent to the existing Session HTTP/2 executor. */
  public <T extends AlipayResponse> T executeWithHeaders(
      AlipayRequest<T> request, Map<String, String> extraHeaders) throws AlipayApiException {
    if (request == null) {
      throw new AlipayApiException("alipayRequest can't null");
    }
    if (RequestTransportResolver.requiresSessionHttp2(request)) {
      return SessionHttp2Executor.execute(auth.gatewayUrl, request, extraHeaders);
    }
    try {
      if (!"POST".equalsIgnoreCase(request.getHttpMethod())) {
        throw new AlipayApiException("Only POST is supported for ordinary API requests");
      }
      HttpPost post = new HttpPost(auth.gatewayUrl + auth.path(request.getPath()));
      post.setHeader("Authorization", auth.authorization());
      post.setHeader("User-Agent", SdkVersion.getUserAgent());
      if (extraHeaders != null) {
        for (Map.Entry<String, String> entry : extraHeaders.entrySet()) {
          ApiKeyAuth.validateHeader(entry.getKey(), entry.getValue());
          if (!ApiKeyAuth.reserved(entry.getKey())) {
            post.setHeader(entry.getKey(), entry.getValue());
          }
        }
      }
      post.setEntity(new StringEntity(JsonUtil.toJson(request), ContentType.APPLICATION_JSON));
      RequestConfig config =
          RequestConfig.custom()
              .setConnectTimeout(Timeout.ofMilliseconds(connectTimeoutMillis))
              .setResponseTimeout(Timeout.ofMilliseconds(readTimeoutMillis))
              .build();
      // A scoped client releases connections even on parsing and transport failures.
      try (CloseableHttpClient client =
          HttpClients.custom()
              .setDefaultRequestConfig(config)
              .disableRedirectHandling()
              .disableAutomaticRetries()
              .disableCookieManagement()
              .build()) {
        return client.execute(
            post,
            response -> {
              String body =
                  response.getEntity() == null
                      ? ""
                      : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
              if (response.getCode() != 200) {
                throw new java.io.IOException(
                    "Response data error, HTTP " + response.getCode() + ": " + auth.redact(body));
              }
              try {
                T parsed = JsonUtil.fromJson(body, request.getResponseClass());
                if (parsed == null || parsed.getResult() == null) {
                  throw new AlipayApiException("Response data error, result field is null");
                }
                return parsed;
              } catch (AlipayApiException e) {
                throw new java.io.IOException(auth.redact(e.getMessage()), auth.safeCause(e));
              }
            });
      }
    } catch (Exception e) {
      // Preserve diagnostics unless the exception chain contains the API Key.
      throw new AlipayApiException(auth.redact(e.getMessage()), auth.safeCause(e));
    }
  }

  /** Optional configuration. Neither this builder nor the client exposes the key. */
  public static final class Builder {
    private final String gatewayUrl;
    private final String apiKey;
    private int connectTimeoutMillis = 15000;
    private int readTimeoutMillis = 30000;

    private Builder(String gatewayUrl, String apiKey) {
      this.gatewayUrl = gatewayUrl;
      this.apiKey = apiKey;
    }

    public Builder connectTimeoutMillis(int value) {
      connectTimeoutMillis = value;
      return this;
    }

    public Builder readTimeoutMillis(int value) {
      readTimeoutMillis = value;
      return this;
    }

    public ApiKeyAlipayClient build() {
      return new ApiKeyAlipayClient(this);
    }
  }
}
