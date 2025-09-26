package com.alipay.global.api.tools;

import com.alipay.global.api.exception.AlipayApiException;

public class WebhookTool {

  /**
   * Check webhook signature
   *
   * @param requestUri your webhook endpoint, domain part excluded, sample: /payNotify
   * @param httpMethod http method
   * @param clientId your clientId, sample: SANDBOX_5X00000000000000
   * @param requestTime requestTime from http header, sample: 2019-01-01T01:01:01Z
   * @param signature signature from http header, sample:
   *     algorithm=RSA256,keyVersion=1,signature=xxx
   * @param notifyBody notify body
   * @param alipayPublicKey alipay public key
   * @return
   * @throws AlipayApiException
   */
  public static boolean checkSignature(
      String requestUri,
      String httpMethod,
      String clientId,
      String requestTime,
      String signature,
      String notifyBody,
      String alipayPublicKey)
      throws AlipayApiException {
    String realSignature = "";

    // get valid part from raw signature
    if (signature == null || signature.isEmpty()) {
      throw new RuntimeException("empty notify signature");
    } else {
      String[] parts = signature.split("signature=");
      if (parts.length > 1) {
        realSignature = parts[1];
      }
    }

    try {
      // verify signature
      return SignatureTool.verify(
          httpMethod,
          requestUri,
          clientId,
          requestTime,
          notifyBody,
          realSignature,
          alipayPublicKey);
    } catch (Exception e) {
      throw new AlipayApiException(e);
    }
  }
}
