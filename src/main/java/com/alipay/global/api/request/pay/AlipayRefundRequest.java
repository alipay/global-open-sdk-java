package com.alipay.global.api.request.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.global.api.model.Amount;
import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.pay.AlipayRefundResponse;

public class AlipayRefundRequest extends AlipayRequest<AlipayRefundResponse> {

    @JSONField(serialize = false)
    private final static String httpMethod = HttpMethod.POST.name();

    private String  refundRequestId;
    private String  paymentId;
    private String  referenceRefundId;
    private Amount refundAmount;
    private String  refundReason;
    private Boolean isAsyncRefund;
    private String  extendInfo;

    public String getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(String refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getReferenceRefundId() {
        return referenceRefundId;
    }

    public void setReferenceRefundId(String referenceRefundId) {
        this.referenceRefundId = referenceRefundId;
    }

    public Amount getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Amount refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Boolean getIsAsyncRefund() {
        return isAsyncRefund;
    }

    public void setIsAsyncRefund(Boolean isAsyncRefund) {
        this.isAsyncRefund = isAsyncRefund;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Class<AlipayRefundResponse> getResponseClass() {
        return AlipayRefundResponse.class;
    }
}
