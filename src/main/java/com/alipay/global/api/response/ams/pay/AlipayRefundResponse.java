package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Quote;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayRefundResponse extends AlipayResponse {

    private String refundRequestId;
    private String refundId;
    private String paymentId;
    private Amount refundAmount;
    private String refundTime;
    private Amount refundNonGuaranteeCouponAmount;
    private Amount grossSettlementAmount;
    private Quote  settlementQuote;

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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public Amount getRefundNonGuaranteeCouponAmount() {
        return refundNonGuaranteeCouponAmount;
    }

    public void setRefundNonGuaranteeCouponAmount(Amount refundNonGuaranteeCouponAmount) {
        this.refundNonGuaranteeCouponAmount = refundNonGuaranteeCouponAmount;
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
