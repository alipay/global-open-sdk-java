package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.net.DefaultHttpRPC;
import com.alipay.global.api.net.HttpRpcResult;
import com.alipay.global.api.request.AlipayFileRequest;
import com.alipay.global.api.response.AlipayResponse;
import java.util.Map;

public class DefaultAlipayClient extends BaseAlipayClient {

  private volatile String uploadGatewayUrl;

  public DefaultAlipayClient(String gatewayUrl, String merchantPrivateKey, String alipayPublicKey) {
    super(gatewayUrl, merchantPrivateKey, alipayPublicKey);
  }

  public DefaultAlipayClient(
      String gatewayUrl, String merchantPrivateKey, String alipayPublicKey, String clientId) {
    super(gatewayUrl, merchantPrivateKey, alipayPublicKey, clientId);
  }

  public DefaultAlipayClient(
      String gatewayUrl,
      String merchantPrivateKey,
      String alipayPublicKey,
      String clientId,
      String agentToken) {
    super(gatewayUrl, merchantPrivateKey, alipayPublicKey, clientId, agentToken);
  }

  @Override
  public Map<String, String> buildCustomHeader() {
    return null;
  }

  /**
   * Overrides the file gateway derived from the normal regional gateway.
   *
   * @param uploadGatewayUrl an absolute HTTPS base URL without user info, path, query, or fragment
   */
  public void setUploadGatewayUrl(String uploadGatewayUrl) {
    this.uploadGatewayUrl = UploadGatewayResolver.normalizeExplicit(uploadGatewayUrl);
  }

  /**
   * Uploads a file using an SDK-provided file request.
   *
   * @param request the SDK-provided request containing the file and business fields
   * @param <T> the response type associated with the request
   * @return the verified API response
   * @throws AlipayApiException when validation, transport, parsing, or signature verification fails
   */
  public <T extends AlipayResponse> T uploadFile(AlipayFileRequest<T> request)
      throws AlipayApiException {
    return FileUploadExecutor.execute(
        new FileUploadClientContext(this), uploadGatewayUrl, request);
  }

  public HttpRpcResult sendRequest(
      String requestUrl, String httpMethod, Map<String, String> header, String reqBody)
      throws AlipayApiException {
    HttpRpcResult httpRpcResult;
    try {
      httpRpcResult = DefaultHttpRPC.doPost(requestUrl, header, reqBody);
    } catch (Exception e) {
      throw new AlipayApiException(e);
    }
    return httpRpcResult;
  }
}
