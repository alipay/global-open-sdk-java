package com.alipay.global.api.response.ams.pay;


import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.TransactionStatusType;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayInquiryRefundResponse extends AlipayResponse {

    private String                refundId;
    private String                refundRequestId;
    private Amount                refundAmount;
    /**
     * refundStatus
     * -SUCCESS
     * -PROCESSING
     */
    private TransactionStatusType refundStatus;
    private String                refundTime;

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

    public Amount getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Amount refundAmount) {
        this.refundAmount = refundAmount;
    }

    public TransactionStatusType getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(TransactionStatusType refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }
}
