package com.alipay.api.response.pay;

import com.alipay.api.model.Amount;
import com.alipay.api.response.AlipayResponse;

public class AlipayRefundResponse extends AlipayResponse {

    private String refundRequestId;
    private String refundId;
    private String paymentId;
    private Amount refundAmount;
    private String refundTime;
    private Amount refundNonGuaranteeCouponAmount;

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

}
