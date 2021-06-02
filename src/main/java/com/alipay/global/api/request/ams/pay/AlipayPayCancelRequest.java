package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayCancelResponse;

public class AlipayPayCancelRequest extends AlipayRequest<AlipayPayCancelResponse> {

    private String paymentId;
    private String paymentRequestId;

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

    @Override
    public Class<AlipayPayCancelResponse> getResponseClass() {
        return AlipayPayCancelResponse.class;
    }

}
