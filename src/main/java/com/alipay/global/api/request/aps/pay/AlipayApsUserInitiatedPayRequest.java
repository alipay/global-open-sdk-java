package com.alipay.global.api.request.aps.pay;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.aps.pay.AlipayApsUserInitiatedPayResponse;

public class AlipayApsUserInitiatedPayRequest
    extends AlipayRequest<AlipayApsUserInitiatedPayResponse> {

  private String acquirerId;
  private String pspId;
  private String codeValue;
  private String customerId;

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

  public String getCodeValue() {
    return codeValue;
  }

  public void setCodeValue(String codeValue) {
    this.codeValue = codeValue;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  @Override
  public Class<AlipayApsUserInitiatedPayResponse> getResponseClass() {
    return AlipayApsUserInitiatedPayResponse.class;
  }
}
