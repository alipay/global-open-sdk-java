package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayRefundResponse;

public class AlipayRefundRequest extends AlipayRequest<AlipayRefundResponse> {

    /**
     * The unique ID assigned by the merchant to identify a refund request
     */
    private String  refundRequestId;

    /**
     * The unique ID assigned by Alipay for the original payment to be refunded
     */
    private String  paymentId;

    /**
     * The unique ID to identify a refund, which is assigned by the merchant that directly provides services or goods to the customer
     */
    private String  referenceRefundId;

    /**
     * The refund amount that is initiated by the merchant
     */
    private Amount  refundAmount;

    /**
     * The refund reason
     */
    private String  refundReason;

    /**
     * The URL that is used to receive the refund result notification. The URL must be either specified in the request or set in Alipay Developer Center
     */
    private String refundNotifyUrl;

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

    public String getRefundNotifyUrl() {
        return refundNotifyUrl;
    }

    public void setRefundNotifyUrl(String refundNotifyUrl) {
        this.refundNotifyUrl = refundNotifyUrl;
    }

    @Override
    public Class<AlipayRefundResponse> getResponseClass() {
        return AlipayRefundResponse.class;
    }
}
