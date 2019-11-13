package com.alipay.global.api.response.pay;

import com.alipay.global.api.model.Amount;
import com.alipay.global.api.model.TransactionStatusType;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayRefundQueryResponse extends AlipayResponse {

    private String                refundId;
    private String                refundRequestId;
    private String                paymentId;
    private String                referenceRefundId;
    private Amount refundAmount;
    private String                refundReason	;
    private TransactionStatusType refundStatus;
    private Amount                refundNonGuaranteeCouponAmount;
    private String                refundTime;
    private String                refundClearingTime;

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

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

    public TransactionStatusType getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(TransactionStatusType refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Amount getRefundNonGuaranteeCouponAmount() {
        return refundNonGuaranteeCouponAmount;
    }

    public void setRefundNonGuaranteeCouponAmount(Amount refundNonGuaranteeCouponAmount) {
        this.refundNonGuaranteeCouponAmount = refundNonGuaranteeCouponAmount;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getRefundClearingTime() {
        return refundClearingTime;
    }

    public void setRefundClearingTime(String refundClearingTime) {
        this.refundClearingTime = refundClearingTime;
    }

}
