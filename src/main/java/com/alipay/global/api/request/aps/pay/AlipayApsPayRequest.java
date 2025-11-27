package com.alipay.global.api.request.aps.pay;

import com.alipay.global.api.model.aps.*;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.aps.pay.AlipayApsPayResponse;
import java.util.List;

public class AlipayApsPayRequest extends AlipayRequest<AlipayApsPayResponse> {

  private Order order;
  private String paymentRequestId;
  private Amount paymentAmount;
  private PaymentMethod paymentMethod;
  private PaymentFactor paymentFactor;
  private String paymentExpiryTime;
  private String paymentNotifyUrl;
  private String paymentRedirectUrl;
  private SettlementStrategy settlementStrategy;
  private List<String> allowedPspRegions;
  private String userRegion;

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public String getPaymentRequestId() {
    return paymentRequestId;
  }

  public void setPaymentRequestId(String paymentRequestId) {
    this.paymentRequestId = paymentRequestId;
  }

  public Amount getPaymentAmount() {
    return paymentAmount;
  }

  public void setPaymentAmount(Amount paymentAmount) {
    this.paymentAmount = paymentAmount;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public PaymentFactor getPaymentFactor() {
    return paymentFactor;
  }

  public void setPaymentFactor(PaymentFactor paymentFactor) {
    this.paymentFactor = paymentFactor;
  }

  public String getPaymentExpiryTime() {
    return paymentExpiryTime;
  }

  public void setPaymentExpiryTime(String paymentExpiryTime) {
    this.paymentExpiryTime = paymentExpiryTime;
  }

  public String getPaymentNotifyUrl() {
    return paymentNotifyUrl;
  }

  public void setPaymentNotifyUrl(String paymentNotifyUrl) {
    this.paymentNotifyUrl = paymentNotifyUrl;
  }

  public String getPaymentRedirectUrl() {
    return paymentRedirectUrl;
  }

  public void setPaymentRedirectUrl(String paymentRedirectUrl) {
    this.paymentRedirectUrl = paymentRedirectUrl;
  }

  public SettlementStrategy getSettlementStrategy() {
    return settlementStrategy;
  }

  public void setSettlementStrategy(SettlementStrategy settlementStrategy) {
    this.settlementStrategy = settlementStrategy;
  }

  public List<String> getAllowedPspRegions() {
    return allowedPspRegions;
  }

  public void setAllowedPspRegions(List<String> allowedPspRegions) {
    this.allowedPspRegions = allowedPspRegions;
  }

  public String getUserRegion() {
    return userRegion;
  }

  public void setUserRegion(String userRegion) {
    this.userRegion = userRegion;
  }

  @Override
  public Class<AlipayApsPayResponse> getResponseClass() {
    return AlipayApsPayResponse.class;
  }
}
