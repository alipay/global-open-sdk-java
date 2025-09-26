package com.alipay.global.api.example.legacy;

import com.alibaba.fastjson.JSON;
import com.alipay.global.api.example.model.*;
import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ResultStatusType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class PayNotifyListener {

  public void acceptNotify(HttpRequest request, HttpResponse response) {

    InputStream inputStream = request.getInputStream();
    String reqBody = read(inputStream);

    PayNotifyRequest payNotifyRequest = JSON.parseObject(reqBody, PayNotifyRequest.class);

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
          .write(JSON.toJSONString(payNotifyResponse).getBytes(Charset.forName("UTF-8")));
    } catch (IOException e) {
    }
  }

  public String read(InputStream inputStream) {

    return null;
  }
}
