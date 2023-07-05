package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Quote;

public class AlipayRefundNotify extends AlipayNotify{

    /**
     * Indicates the refund result. SUCCESS/FAIL
     */
    private String refundStatus;

    /**
     * The unique ID assigned by a merchant to identify a refund request
     */
    private String refundRequestId;

    /**
     * The unique ID that is assigned by Alipay to identify a refund. A one-to-one correspondence between paymentId and paymentRequestId exists
     */
    private String refundId;

    /**
     * The refund amount that is initiated by the merchant
     */
    private Amount refundAmount;

    /**
     * The date and time when the refund reaches a final state of success or failure
     */
    private String refundTime;

    /**
     * The refund settlement amount, which equals the refund amount multiplied by the value of settlementQuote
     */
    private Amount grossSettlementAmount;

    /**
     * The exchange rate between the settlement currency and transaction currency
     */
    private Quote settlementQuote;

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(String refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Amount getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Amount refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public Amount getGrossSettlementAmount() {
        return grossSettlementAmount;
    }

    public void setGrossSettlementAmount(Amount grossSettlementAmount) {
        this.grossSettlementAmount = grossSettlementAmount;
    }

    public Quote getSettlementQuote() {
        return settlementQuote;
    }

    public void setSettlementQuote(Quote settlementQuote) {
        this.settlementQuote = settlementQuote;
    }
}
