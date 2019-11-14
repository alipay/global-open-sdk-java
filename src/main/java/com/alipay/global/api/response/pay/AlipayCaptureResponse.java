package com.alipay.global.api.response.pay;

import com.alipay.global.api.model.Amount;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayCaptureResponse  extends AlipayResponse {

    private String  paymentRequestId;
    private String  paymentId;
    private String  authPaymentId;
    private Amount  paymentAmount;
    private String  paymentTime;
    private String  extendInfo;

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

    public String getAuthPaymentId() {
        return authPaymentId;
    }

    public void setAuthPaymentId(String authPaymentId) {
        this.authPaymentId = authPaymentId;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }
}
