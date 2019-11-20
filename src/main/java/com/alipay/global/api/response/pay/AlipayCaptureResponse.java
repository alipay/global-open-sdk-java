package com.alipay.global.api.response.pay;

import com.alipay.global.api.model.Amount;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayCaptureResponse  extends AlipayResponse {

    private String  captureRequestId;
    private String  captureId;
    private String  paymentId;
    private Amount  captureAmount;
    private String  captureTime;

    public String getCaptureRequestId() {
        return captureRequestId;
    }

    public void setCaptureRequestId(String captureRequestId) {
        this.captureRequestId = captureRequestId;
    }

    public String getCaptureId() {
        return captureId;
    }

    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Amount getCaptureAmount() {
        return captureAmount;
    }

    public void setCaptureAmount(Amount captureAmount) {
        this.captureAmount = captureAmount;
    }

    public String getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }
}
