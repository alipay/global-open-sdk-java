package com.alipay.global.api.request.aps.refund;

import com.alipay.global.api.model.aps.Amount;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.aps.refund.AlipayApsRefundResponse;

public class AlipayApsRefundRequest extends AlipayRequest<AlipayApsRefundResponse> {

  private String paymentRequestId;
  private String paymentId;
  private String refundRequestId;
  private Amount refundAmount;
  private String refundReason;

  public String getPaymentRequestId() {
    return paymentRequestId;
  }

  public void setPaymentRequestId(String paymentRequestId) {
    this.paymentRequestId = paymentRequestId;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getRefundRequestId() {
    return refundRequestId;
  }

  public void setRefundRequestId(String refundRequestId) {
    this.refundRequestId = refundRequestId;
  }

  public Amount getRefundAmount() {
    return refundAmount;
  }

  public void setRefundAmount(Amount refundAmount) {
    this.refundAmount = refundAmount;
  }

  public String getRefundReason() {
    return refundReason;
  }

  public void setRefundReason(String refundReason) {
    this.refundReason = refundReason;
  }

  @Override
  public Class<AlipayApsRefundResponse> getResponseClass() {
    return AlipayApsRefundResponse.class;
  }
}
