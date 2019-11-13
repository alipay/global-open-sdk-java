package com.alipay.global.api.model;

public class PaymentVerificationData {
    private String verifyRequestId;
    private String authenticationCode;

    public String getVerifyRequestId() {
        return verifyRequestId;
    }

    public void setVerifyRequestId(String verifyRequestId) {
        this.verifyRequestId = verifyRequestId;
    }

    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public void setAuthenticationCode(String authenticationCode) {
        this.authenticationCode = authenticationCode;
    }

}
