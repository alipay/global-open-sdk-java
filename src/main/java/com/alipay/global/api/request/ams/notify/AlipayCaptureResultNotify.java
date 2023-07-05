package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;

public class AlipayCaptureResultNotify extends AlipayNotify{

    private String captureRequestId;

    private String paymentId;

    private Amount captureAmount;

    public String getCaptureRequestId() {
        return captureRequestId;
    }

    public void setCaptureRequestId(String captureRequestId) {
        this.captureRequestId = captureRequestId;
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
}
