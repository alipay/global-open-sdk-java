package com.alipay.global.api.response.ams.order;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.RedirectActionForm;
import com.alipay.global.api.response.AlipayResponse;

@Deprecated
public class AlipayCreateOrderResponse extends AlipayResponse {

  private String paymentId;
  private String paymentRequestId;
  private String clientPaymentToken;
  private Amount paymentAmount;
  private RedirectActionForm redirectActionForm;

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getPaymentRequestId() {
    return paymentRequestId;
  }

  public void setPaymentRequestId(String paymentRequestId) {
    this.paymentRequestId = paymentRequestId;
  }

  public String getClientPaymentToken() {
    return clientPaymentToken;
  }

  public void setClientPaymentToken(String clientPaymentToken) {
    this.clientPaymentToken = clientPaymentToken;
  }

  public Amount getPaymentAmount() {
    return paymentAmount;
  }

  public void setPaymentAmount(Amount paymentAmount) {
    this.paymentAmount = paymentAmount;
  }

  public RedirectActionForm getRedirectActionForm() {
    return redirectActionForm;
  }

  public void setRedirectActionForm(RedirectActionForm redirectActionForm) {
    this.redirectActionForm = redirectActionForm;
  }
}
