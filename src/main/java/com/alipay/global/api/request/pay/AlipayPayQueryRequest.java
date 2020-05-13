package com.alipay.global.api.request.pay;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.pay.AlipayPayQueryResponse;

public class AlipayPayQueryRequest extends AlipayRequest<AlipayPayQueryResponse> {

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
    public Class<AlipayPayQueryResponse> getResponseClass() {
        return AlipayPayQueryResponse.class;
    }
}
