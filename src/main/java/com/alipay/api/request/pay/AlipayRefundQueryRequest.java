package com.alipay.api.request.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.api.net.HttpMethod;
import com.alipay.api.request.AlipayRequest;
import com.alipay.api.response.pay.AlipayRefundQueryResponse;

public class AlipayRefundQueryRequest extends AlipayRequest<AlipayRefundQueryResponse> {

    @JSONField(serialize = false)
    private final static String httpMethod = HttpMethod.POST.name();

    private String refundId;

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Class<AlipayRefundQueryResponse> getResponseClass() {
        return AlipayRefundQueryResponse.class;
    }

}
