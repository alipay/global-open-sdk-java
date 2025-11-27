package com.alipay.global.api.response.aps.pay;

import com.alipay.global.api.model.aps.Amount;
import com.alipay.global.api.model.aps.OrderCodeForm;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayApsPayResponse extends AlipayResponse {

  private String acquirerId;
  private String paymentId;
  private String paymentTime;
  private String paymentUrl;
  private Amount paymentAmount;
  private OrderCodeForm orderCodeForm;
  private String customerId;
  private String pspId;
  private String walletBrandName;

  public String getAcquirerId() {
    return acquirerId;
  }

  public void setAcquirerId(String acquirerId) {
    this.acquirerId = acquirerId;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getPaymentTime() {
    return paymentTime;
  }

  public void setPaymentTime(String paymentTime) {
    this.paymentTime = paymentTime;
  }

  public Amount getPaymentAmount() {
    return paymentAmount;
  }

  public void setPaymentAmount(Amount paymentAmount) {
    this.paymentAmount = paymentAmount;
  }

  public String getPaymentUrl() {
    return paymentUrl;
  }

  public void setPaymentUrl(String paymentUrl) {
    this.paymentUrl = paymentUrl;
  }

  public OrderCodeForm getOrderCodeForm() {
    return orderCodeForm;
  }

  public void setOrderCodeForm(OrderCodeForm orderCodeForm) {
    this.orderCodeForm = orderCodeForm;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getPspId() {
    return pspId;
  }

  public void setPspId(String pspId) {
    this.pspId = pspId;
  }

  public String getWalletBrandName() {
    return walletBrandName;
  }

  public void setWalletBrandName(String walletBrandName) {
    this.walletBrandName = walletBrandName;
  }
}
