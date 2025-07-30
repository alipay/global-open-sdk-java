package com.alipay.global.api.net;

public class HttpRpcResult {

  private int rspCode;
  private String rspBody;
  private String rspSign;
  private String responseTime;

  public int getRspCode() {
    return rspCode;
  }

  public void setRspCode(int rspCode) {
    this.rspCode = rspCode;
  }

  public String getRspBody() {
    return rspBody;
  }

  public void setRspBody(String rspBody) {
    this.rspBody = rspBody;
  }

  public String getRspSign() {
    return rspSign;
  }

  public void setRspSign(String rspSign) {
    this.rspSign = rspSign;
  }

  public String getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(String responseTime) {
    this.responseTime = responseTime;
  }
}
