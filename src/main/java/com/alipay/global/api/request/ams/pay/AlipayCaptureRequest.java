package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayCaptureResponse;

public class AlipayCaptureRequest extends AlipayRequest<AlipayCaptureResponse> {

    private String  captureRequestId;
    private String  paymentId;
    private Amount  captureAmount;
    private Boolean isLastCapture;

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

    public Boolean getLastCapture() {
        return isLastCapture;
    }

    public void setLastCapture(Boolean lastCapture) {
        isLastCapture = lastCapture;
    }

    @Override
    public Class<AlipayCaptureResponse> getResponseClass() {
        return AlipayCaptureResponse.class;
    }
}
