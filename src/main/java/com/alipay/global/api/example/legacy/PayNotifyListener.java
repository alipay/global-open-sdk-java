package com.alipay.global.api.example.legacy;

import com.alipay.global.api.example.model.*;
import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ResultStatusType;
import com.alipay.global.api.tools.JsonUtil;
import com.alipay.global.api.tools.WebhookTool;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class PayNotifyListener {
  // Replace these values with the configured notification endpoint and credentials.
  private static final String NOTIFY_PATH = "/payNotify";
  private static final String CLIENT_ID = "";
  private static final String ANTOM_PUBLIC_KEY = "";

  public void acceptNotify(HttpRequest request, HttpResponse response) {

    final PayNotifyRequest payNotifyRequest;
    try {
      String reqBody = read(request.getInputStream());
      if (!WebhookTool.checkSignature(
          NOTIFY_PATH,
          "POST",
          CLIENT_ID,
          request.getHeader("Request-Time"),
          request.getHeader("Signature"),
          reqBody,
          ANTOM_PUBLIC_KEY)) {
        return;
      }
      payNotifyRequest = JsonUtil.fromJson(reqBody, PayNotifyRequest.class);
    } catch (AlipayApiException | RuntimeException e) {
      return;
    }
    if (payNotifyRequest == null || payNotifyRequest.getResultInfo() == null) {
      return;
    }

    if (!PaymentNotifyType.PAYMENT_RESULT.equals(payNotifyRequest.getNotifyType())) {
      return;
    }
    Result resultInfo = payNotifyRequest.getResultInfo();
    String paymentRequestId = payNotifyRequest.getPaymentRequestId();
    ResultStatusType resultStatus = resultInfo.getResultStatus();

    boolean isAcceptSuccess = false;
    if (ResultStatusType.S.equals(resultStatus)) {
      // TODO Update the record status to success by paymentRequestId
      isAcceptSuccess = true;
    } else if (ResultStatusType.F.equals(resultStatus)) {
      // TODO Update the record status to fail by paymentRequestId
      isAcceptSuccess = true;
    } else {
      // TODO Notify exception, contact tech support
      return;
    }

    Result result = new Result("SUCCESS", ResultStatusType.S, "success");
    if (!isAcceptSuccess) {
      result = new Result("PROCESS_FAIL", ResultStatusType.F, "failure");
    }
    try {
      PayNotifyResponse payNotifyResponse = new PayNotifyResponse();
      payNotifyResponse.setResult(result);
      response
          .getOutputStream()
          .write(JsonUtil.toJson(payNotifyResponse).getBytes(Charset.forName("UTF-8")));
    } catch (IOException | AlipayApiException e) {
    }
  }

  public String read(InputStream inputStream) {
    try {
      ByteArrayOutputStream body = new ByteArrayOutputStream();
      byte[] buffer = new byte[4096];
      int count;
      while ((count = inputStream.read(buffer)) != -1) {
        body.write(buffer, 0, count);
      }
      return new String(body.toByteArray(), Charset.forName("UTF-8"));
    } catch (IOException e) {
      throw new IllegalStateException("Unable to read notification body", e);
    }
  }
}
