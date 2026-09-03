package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.Result;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.AlipayResponse;
import com.alipay.global.api.tools.JsonUtil;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

final class SessionHttp2Executor {

  private static final String SESSION_HEADER = "X-Session-Id";

  private SessionHttp2Executor() {}

  static <T extends AlipayResponse> T execute(
      String gatewayUrl, AlipayRequest<T> request, Map<String, String> extraHeaders)
      throws AlipayApiException {
    String sessionId = validateAndGetSessionId(extraHeaders);
    String requestBody = JsonUtil.toJson(request);
    String responseBody =
        Http2JsonTransport.post(gatewayUrl, request.getPath(), sessionId, requestBody);

    T response;
    try {
      response = JsonUtil.fromJson(responseBody, request.getResponseClass());
    } catch (AlipayApiException e) {
      throw new AlipayApiException("Failed to parse API response.", e);
    }
    Result result = response == null ? null : response.getResult();
    if (result == null) {
      throw new AlipayApiException(
          "Response data error, result field is null, rspBody:" + responseBody);
    }
    return response;
  }

  private static String validateAndGetSessionId(Map<String, String> extraHeaders)
      throws AlipayApiException {
    String sessionId = null;
    if (extraHeaders != null) {
      for (Map.Entry<String, String> entry : extraHeaders.entrySet()) {
        String name = entry.getKey();
        if (name == null) {
          throw new AlipayApiException("Header name can't be null.");
        }
        if (!SESSION_HEADER.equalsIgnoreCase(name)) {
          throw new AlipayApiException(
              "Only X-Session-Id is supported for this API. Unsupported header: " + name);
        }
        if (sessionId != null) {
          throw new AlipayApiException("X-Session-Id must be provided only once.");
        }
        sessionId = entry.getValue();
      }
    }
    if (StringUtils.isBlank(sessionId)) {
      throw new AlipayApiException("X-Session-Id can't be null or blank.");
    }
    if (sessionId.indexOf('\r') >= 0 || sessionId.indexOf('\n') >= 0) {
      throw new AlipayApiException("X-Session-Id cannot contain CR or LF characters.");
    }
    return sessionId;
  }
}
