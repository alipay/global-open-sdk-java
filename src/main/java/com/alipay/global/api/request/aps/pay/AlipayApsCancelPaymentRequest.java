package com.alipay.global.api.request.aps.pay;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.aps.pay.AlipayApsCancelPaymentResponse;

public class AlipayApsCancelPaymentRequest extends AlipayRequest<AlipayApsCancelPaymentResponse> {

    private String paymentRequestId;
    private String paymentId;

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

    @Override
    public Class<AlipayApsCancelPaymentResponse> getResponseClass() {
        return AlipayApsCancelPaymentResponse.class;
    }

}
