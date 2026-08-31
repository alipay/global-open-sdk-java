package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.Result;
import com.alipay.global.api.net.HttpRpcResult;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.AlipayResponse;
import com.alipay.global.api.tools.Constants;
import com.alipay.global.api.tools.DateTool;
import com.alipay.global.api.tools.JsonUtil;
import com.alipay.global.api.tools.SdkVersion;
import com.alipay.global.api.tools.SignatureTool;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public abstract class BaseAlipayClient implements AlipayClient {

  private static final Integer DEFAULT_KEY_VERSION = 1;

  /**
   * Path prefixes that must use the production gateway even in sandbox mode.
   *
   * <p>Billing and Meter APIs were previously listed here because the sandbox did not support them.
   * As of 2026-08-05, the sandbox now fully supports both, so this set is empty. The filtering
   * logic ({@link #shouldUseProductionPathInSandbox}) is kept intact so that future APIs that
   * require production-only routing can be added here without modifying the call chain.
   */
  private static final Set<String> SANDBOX_PRODUCTION_PATH_PREFIXES = new HashSet<>();

  /** eg: https://open-na.alipay.com */
  private String gatewayUrl;

  /** merchants private key */
  private String merchantPrivateKey;

  /** alipay public key */
  private String alipayPublicKey;

  /** client id */
  private String clientId;

  /** is sandbox mode */
  private boolean isSandboxMode = false;

  private String agentToken;

  public BaseAlipayClient() {}

  public BaseAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey) {
    this.gatewayUrl = gatewayUrl;
    this.merchantPrivateKey = merchantPrivateKey;
    this.alipayPublicKey = alipayPublicKey;
  }

  public BaseAlipayClient(
      String gatewayUrl, String merchantPrivateKey, String alipayPublicKey, String clientId) {
    this.gatewayUrl = gatewayUrl;
    this.merchantPrivateKey = merchantPrivateKey;
    this.alipayPublicKey = alipayPublicKey;
    this.clientId = clientId;

    // if client id starts with SANDBOX_, set to sandbox mode
    if (clientId.startsWith("SANDBOX_")) {
      this.isSandboxMode = true;
    }
  }

  public BaseAlipayClient(
      String gatewayUrl,
      String merchantPrivateKey,
      String alipayPublicKey,
      String clientId,
      String agentToken) {
    this.gatewayUrl = gatewayUrl;
    this.merchantPrivateKey = merchantPrivateKey;
    this.alipayPublicKey = alipayPublicKey;
    this.clientId = clientId;
    this.agentToken = agentToken;

    // if client id starts with SANDBOX_, set to sandbox mode
    if (clientId.startsWith("SANDBOX_")) {
      this.isSandboxMode = true;
    }
  }

  public <T extends AlipayResponse> T execute(AlipayRequest<T> alipayRequest)
      throws AlipayApiException {

    // compatible with old version which clientId does not exist in BaseAlipayClient
    alipayRequest.setClientId(
        alipayRequest.getClientId() == null ? this.clientId : alipayRequest.getClientId());

    // replace with sandbox url if needed
    adjustSandboxUrl(alipayRequest);

    // check request params
    checkRequestParams(alipayRequest);

    String clientId = alipayRequest.getClientId();
    String httpMethod = alipayRequest.getHttpMethod();
    String path = alipayRequest.getPath();
    Integer keyVersion = alipayRequest.getKeyVersion();
    String reqTime = DateTool.getCurrentTimeMillis();
    String reqBody = JsonUtil.toJson(alipayRequest);

    /** 对内容加签(Sign the content) */
    String signValue = genSignValue(httpMethod, path, clientId, reqTime, reqBody);

    /** 生成必要header(Generate required headers) */
    Map<String, String> header = buildBaseHeader(reqTime, clientId, keyVersion, signValue);
    Map<String, String> customHeader = buildCustomHeader();
    if (customHeader != null && !customHeader.isEmpty()) {
      header.putAll(customHeader);
    }
    applySdkUserAgent(header);

    String requestUrl = genRequestUrl(path);
    /** 向网关发起http请求(Make an HTTP request to the gateway) */
    HttpRpcResult rsp = sendRequest(requestUrl, httpMethod, header, reqBody);

    if (rsp == null) {
      throw new AlipayApiException("HttpRpcResult is null.");
    }

    int httpRespCode = rsp.getRspCode();
    String rspBody = rsp.getRspBody();
    if (httpRespCode != Constants.HTTP_SUCCESS_CODE) {
      throw new AlipayApiException("Response data error, rspBody:" + rspBody);
    }
    Class<T> responseClass = alipayRequest.getResponseClass();
    T alipayResponse = JsonUtil.fromJson(rspBody, responseClass);
    Result result = alipayResponse.getResult();
    if (result == null) {
      throw new AlipayApiException("Response data error, result field is null, rspBody:" + rspBody);
    }

    String rspSignValue = rsp.getRspSign();
    String rspTime = rsp.getResponseTime();
    if (null == rspSignValue || rspSignValue.isEmpty() || null == rspTime || rspTime.isEmpty()) {
      return alipayResponse;
    }

    /** 对返回结果验签(Verify the result signature) */
    boolean isVerifySuccess =
        checkRspSign(httpMethod, path, clientId, rspTime, rspBody, rspSignValue);
    if (!isVerifySuccess) {
      throw new AlipayApiException("Response signature verify fail.");
    }

    return alipayResponse;
  }

  private static final Set<String> RESERVED_HEADERS =
      new HashSet<>(
          Arrays.asList(
              "signature",
              "client-id",
              "request-time",
              "content-type",
              "agent-token",
              "user-agent"));

  public <T extends AlipayResponse> T executeWithHeaders(
      AlipayRequest<T> alipayRequest, Map<String, String> extraHeaders) throws AlipayApiException {

    if (RequestTransportResolver.requiresSessionHttp2(alipayRequest)) {
      return SessionHttp2Executor.execute(gatewayUrl, alipayRequest, extraHeaders);
    }

    // compatible with old version which clientId does not exist in BaseAlipayClient
    alipayRequest.setClientId(
        alipayRequest.getClientId() == null ? this.clientId : alipayRequest.getClientId());

    // replace with sandbox url if needed
    adjustSandboxUrl(alipayRequest);

    // check request params
    checkRequestParams(alipayRequest);

    String clientId = alipayRequest.getClientId();
    String httpMethod = alipayRequest.getHttpMethod();
    String path = alipayRequest.getPath();
    Integer keyVersion = alipayRequest.getKeyVersion();
    String reqTime = DateTool.getCurrentTimeMillis();
    String reqBody = JsonUtil.toJson(alipayRequest);

    /** 对内容加签(Sign the content) */
    String signValue = genSignValue(httpMethod, path, clientId, reqTime, reqBody);

    /** 生成必要header(Generate required headers) */
    Map<String, String> header = buildBaseHeader(reqTime, clientId, keyVersion, signValue);
    Map<String, String> customHeader = buildCustomHeader();
    if (customHeader != null && !customHeader.isEmpty()) {
      header.putAll(customHeader);
    }
    if (extraHeaders != null && !extraHeaders.isEmpty()) {
      for (Map.Entry<String, String> entry : extraHeaders.entrySet()) {
        if (entry.getKey() == null) {
          continue;
        }
        if (!RESERVED_HEADERS.contains(entry.getKey().toLowerCase())) {
          header.put(entry.getKey(), entry.getValue());
        }
      }
    }
    applySdkUserAgent(header);

    String requestUrl = genRequestUrl(path);
    /** 向网关发起http请求(Make an HTTP request to the gateway) */
    HttpRpcResult rsp = sendRequest(requestUrl, httpMethod, header, reqBody);

    if (rsp == null) {
      throw new AlipayApiException("HttpRpcResult is null.");
    }

    int httpRespCode = rsp.getRspCode();
    String rspBody = rsp.getRspBody();
    if (httpRespCode != Constants.HTTP_SUCCESS_CODE) {
      throw new AlipayApiException("Response data error, rspBody:" + rspBody);
    }
    Class<T> responseClass = alipayRequest.getResponseClass();
    T alipayResponse = JsonUtil.fromJson(rspBody, responseClass);
    Result result = alipayResponse.getResult();
    if (result == null) {
      throw new AlipayApiException("Response data error, result field is null, rspBody:" + rspBody);
    }

    String rspSignValue = rsp.getRspSign();
    String rspTime = rsp.getResponseTime();
    if (null == rspSignValue || rspSignValue.isEmpty() || null == rspTime || rspTime.isEmpty()) {
      return alipayResponse;
    }

    /** 对返回结果验签(Verify the result signature) */
    boolean isVerifySuccess =
        checkRspSign(httpMethod, path, clientId, rspTime, rspBody, rspSignValue);
    if (!isVerifySuccess) {
      throw new AlipayApiException("Response signature verify fail.");
    }

    return alipayResponse;
  }

  private String genSignValue(
      String httpMethod, String path, String clientId, String requestTime, String reqBody)
      throws AlipayApiException {
    String signatureValue;
    try {
      signatureValue =
          SignatureTool.sign(httpMethod, path, clientId, requestTime, reqBody, merchantPrivateKey);
    } catch (Exception e) {
      throw new AlipayApiException("generate signature error", e);
    }
    return signatureValue;
  }

  private boolean checkRspSign(
      String httpMethod,
      String path,
      String clientId,
      String responseTime,
      String rspBody,
      String rspSignValue)
      throws AlipayApiException {
    try {
      return SignatureTool.verify(
          httpMethod, path, clientId, responseTime, rspBody, rspSignValue, alipayPublicKey);
    } catch (Exception e) {
      throw new AlipayApiException("verify signature error", e);
    }
  }

  private void checkRequestParams(AlipayRequest alipayRequest) throws AlipayApiException {
    if (alipayRequest == null) {
      throw new AlipayApiException("alipayRequest can't null");
    }

    String clientId = alipayRequest.getClientId();
    String httpMethod = alipayRequest.getHttpMethod();
    String path = alipayRequest.getPath();

    if (StringUtils.isBlank(gatewayUrl)) {
      throw new AlipayApiException("gatewayUrl can't null");
    }

    if (StringUtils.isBlank(clientId)) {
      throw new AlipayApiException("clientId can't null");
    }

    if (StringUtils.isBlank(httpMethod)) {
      throw new AlipayApiException("httpMethod can't null");
    }

    if (StringUtils.isBlank(path)) {
      throw new AlipayApiException("path can't null");
    }

    if (!path.startsWith("/")) {
      throw new AlipayApiException("path must start with /");
    }
  }

  private String genRequestUrl(String path) {
    if (!gatewayUrl.startsWith("http://") && !gatewayUrl.startsWith("https://")) {
      gatewayUrl = "https://" + gatewayUrl;
    }
    if (gatewayUrl.endsWith("/")) {
      int len = gatewayUrl.length();
      gatewayUrl = gatewayUrl.substring(0, len - 1);
    }
    return gatewayUrl + path;
  }

  /**
   * If is sandbox mode, modify the path
   *
   * @param alipayRequest
   */
  private void adjustSandboxUrl(AlipayRequest alipayRequest) {
    if (isSandboxMode && alipayRequest.usingSandboxUrl()) {
      String originPath = alipayRequest.getPath();
      if (shouldUseProductionPathInSandbox(originPath)) {
        return;
      }
      alipayRequest.setPath(originPath.replaceFirst("/ams/api", "/ams/sandbox/api"));
    }
  }

  private boolean shouldUseProductionPathInSandbox(String path) {
    for (String prefix : SANDBOX_PRODUCTION_PATH_PREFIXES) {
      if (path.startsWith(prefix)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Generate required headers
   *
   * @param requestTime
   * @param clientId
   * @param keyVersion
   * @param signatureValue
   * @return
   */
  private Map<String, String> buildBaseHeader(
      String requestTime, String clientId, Integer keyVersion, String signatureValue) {
    Map<String, String> header = new HashMap<String, String>();
    header.put(Constants.CONTENT_TYPE_HEADER, "application/json; charset=UTF-8");
    header.put(Constants.USER_AGENT_HEADER, SdkVersion.getUserAgent());
    header.put(Constants.REQ_TIME_HEADER, requestTime);
    header.put(Constants.CLIENT_ID_HEADER, clientId);
    if (keyVersion == null) {
      keyVersion = DEFAULT_KEY_VERSION;
    }
    String signatureHeader =
        "algorithm=RSA256,keyVersion=" + keyVersion + ",signature=" + signatureValue;
    header.put(Constants.REQ_SIGN_HEADER, signatureHeader);
    if (StringUtils.isNotBlank(agentToken)) {
      header.put(Constants.AGENT_TOKEN_HEADER, agentToken);
    }
    return header;
  }

  private void applySdkUserAgent(Map<String, String> header) {
    Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, String> entry = iterator.next();
      if (Constants.USER_AGENT_HEADER.equalsIgnoreCase(entry.getKey())) {
        iterator.remove();
      }
    }
    header.put(Constants.USER_AGENT_HEADER, SdkVersion.getUserAgent());
  }

  /**
   * Narrow internal bridge for the multipart upload path. Existing client internals remain private
   * so adding file upload support does not change the subclass contract of this public base class.
   */
  static final class FileUploadClientContext {

    private final BaseAlipayClient client;

    FileUploadClientContext(BaseAlipayClient client) {
      this.client = client;
    }

    String getConfiguredGatewayUrl() {
      return client.gatewayUrl;
    }

    String getConfiguredClientId() {
      return client.clientId;
    }

    String resolveSandboxPath(String path, boolean useSandboxUrl, String effectiveClientId) {
      if (StringUtils.startsWith(effectiveClientId, "SANDBOX_")
          && useSandboxUrl
          && !client.shouldUseProductionPathInSandbox(path)) {
        return path.replaceFirst("/ams/api", "/ams/sandbox/api");
      }
      return path;
    }

    String sign(
        String httpMethod, String path, String clientId, String requestTime, String requestBody)
        throws AlipayApiException {
      return client.genSignValue(httpMethod, path, clientId, requestTime, requestBody);
    }

    Map<String, String> buildHeaders(
        String requestTime, String clientId, Integer keyVersion, String signatureValue) {
      Map<String, String> headers =
          client.buildBaseHeader(requestTime, clientId, keyVersion, signatureValue);
      Map<String, String> customHeaders = client.buildCustomHeader();
      if (customHeaders != null && !customHeaders.isEmpty()) {
        for (Map.Entry<String, String> entry : customHeaders.entrySet()) {
          if (entry.getKey() != null && !RESERVED_HEADERS.contains(entry.getKey().toLowerCase())) {
            headers.put(entry.getKey(), entry.getValue());
          }
        }
      }
      client.applySdkUserAgent(headers);
      return headers;
    }

    boolean verifyResponse(
        String httpMethod,
        String path,
        String clientId,
        String responseTime,
        String responseBody,
        String responseSignature)
        throws AlipayApiException {
      return client.checkRspSign(
          httpMethod, path, clientId, responseTime, responseBody, responseSignature);
    }
  }

  public abstract Map<String, String> buildCustomHeader();

  public abstract HttpRpcResult sendRequest(
      String requestUrl, String httpMethod, Map<String, String> header, String reqBody)
      throws AlipayApiException;
}
