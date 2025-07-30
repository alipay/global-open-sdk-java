package com.alipay.global.api.example.model;

import com.alipay.global.api.model.Result;

public class PayNotifyRequest {

  private PaymentNotifyType notifyType;
  private Result resultInfo;
  private String paymentRequestId;
  // TODO other field

  public PaymentNotifyType getNotifyType() {
    return notifyType;
  }

  public void setNotifyType(PaymentNotifyType notifyType) {
    this.notifyType = notifyType;
  }

  public Result getResultInfo() {
    return resultInfo;
  }

  public void setResultInfo(Result resultInfo) {
    this.resultInfo = resultInfo;
  }

  public String getPaymentRequestId() {
    return paymentRequestId;
  }

  public void setPaymentRequestId(String paymentRequestId) {
    this.paymentRequestId = paymentRequestId;
  }
}
