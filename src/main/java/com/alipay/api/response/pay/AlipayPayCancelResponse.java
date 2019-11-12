package com.alipay.api.response.pay;

import com.alipay.api.response.AlipayResponse;

import java.util.Date;

public class AlipayPayCancelResponse extends AlipayResponse {

    private String paymentId;
    private String paymentRequestId;
    private Date   cancelTime;

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

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

}
