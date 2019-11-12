package com.alipay.api.request.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.api.net.HttpMethod;
import com.alipay.api.request.AlipayRequest;
import com.alipay.api.response.pay.AlipayPayQueryResponse;

public class AlipayPayQueryRequest extends AlipayRequest<AlipayPayQueryResponse> {

    @JSONField(serialize = false)
    private final static String httpMethod = HttpMethod.POST.name();

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
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Class<AlipayPayQueryResponse> getResponseClass() {
        return AlipayPayQueryResponse.class;
    }
}
