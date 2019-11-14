package com.alipay.global.api.response.pay;

import com.alipay.global.api.response.AlipayResponse;

public class AlipayPayCancelResponse extends AlipayResponse {

    private String paymentId;
    private String paymentRequestId;
    private String cancelTime;

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

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

}
