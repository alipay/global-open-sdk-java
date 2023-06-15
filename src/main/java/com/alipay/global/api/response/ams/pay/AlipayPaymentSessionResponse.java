package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.response.AlipayResponse;

public class AlipayPaymentSessionResponse extends AlipayResponse {

    private String paymentSessionData;

    private String paymentSessionExpiryTime;

    private String paymentSessionId;

    public String getPaymentSessionData() {
        return paymentSessionData;
    }

    public void setPaymentSessionData(String paymentSessionData) {
        this.paymentSessionData = paymentSessionData;
    }

    public String getPaymentSessionExpiryTime() {
        return paymentSessionExpiryTime;
    }

    public void setPaymentSessionExpiryTime(String paymentSessionExpiryTime) {
        this.paymentSessionExpiryTime = paymentSessionExpiryTime;
    }

    public String getPaymentSessionId() {
        return paymentSessionId;
    }

    public void setPaymentSessionId(String paymentSessionId) {
        this.paymentSessionId = paymentSessionId;
    }
}
