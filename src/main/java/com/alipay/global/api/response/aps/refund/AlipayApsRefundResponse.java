package com.alipay.global.api.response.aps.refund;

import com.alipay.global.api.model.aps.Amount;
import com.alipay.global.api.model.aps.Quote;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayApsRefundResponse extends AlipayResponse {

  private String acquirerId;
  private String pspId;
  private String refundId;
  private String refundTime;
  private Amount refundAmount;
  private Amount settlementAmount;
  private Quote settlementQuote;

  public String getAcquirerId() {
    return acquirerId;
  }

  public void setAcquirerId(String acquirerId) {
    this.acquirerId = acquirerId;
  }

  public String getPspId() {
    return pspId;
  }

  public void setPspId(String pspId) {
    this.pspId = pspId;
  }

  public String getRefundId() {
    return refundId;
  }

  public void setRefundId(String refundId) {
    this.refundId = refundId;
  }

  public String getRefundTime() {
    return refundTime;
  }

  public void setRefundTime(String refundTime) {
    this.refundTime = refundTime;
  }

  public Amount getRefundAmount() {
    return refundAmount;
  }

  public void setRefundAmount(Amount refundAmount) {
    this.refundAmount = refundAmount;
  }

  public Amount getSettlementAmount() {
    return settlementAmount;
  }

  public void setSettlementAmount(Amount settlementAmount) {
    this.settlementAmount = settlementAmount;
  }

  public Quote getSettlementQuote() {
    return settlementQuote;
  }

  public void setSettlementQuote(Quote settlementQuote) {
    this.settlementQuote = settlementQuote;
  }
}
