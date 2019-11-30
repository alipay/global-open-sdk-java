package com.alipay.global.api.result;

public class HttpRpcResult {

    private String rspBody;
    private String rspSign;
    private String responseTime;

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
