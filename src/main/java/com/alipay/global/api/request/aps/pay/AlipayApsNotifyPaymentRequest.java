package com.alipay.global.api.request.aps.pay;

import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.aps.Amount;
import com.alipay.global.api.model.aps.Quote;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.aps.pay.AlipayApsNotifyPaymentResponse;

public class AlipayApsNotifyPaymentRequest extends AlipayRequest<AlipayApsNotifyPaymentResponse> {

    private String acquirerId;
    private String pspId;
    private Result paymentResult;
    private String paymentRequestId;
    private String paymentId;
    private Amount paymentAmount;
    private String paymentTime;
    private String customerId;
    private String walletBrandName;
    private Amount settlementAmount;
    private Quote  settlementQuote;

    public String getAcquirerId() {
        return acquirerId;
    }

    public void setAcquirerId(String acquirerId) {
        this.acquirerId = acquirerId;
    }

    public String getPspId() {
        return pspId;
    }

    public void setPspId(String pspId) {
        this.pspId = pspId;
    }

    public Result getPaymentResult() {
        return paymentResult;
    }

    public void setPaymentResult(Result paymentResult) {
        this.paymentResult = paymentResult;
    }

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

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWalletBrandName() {
        return walletBrandName;
    }

    public void setWalletBrandName(String walletBrandName) {
        this.walletBrandName = walletBrandName;
    }

    public Amount getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(Amount settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public Quote getSettlementQuote() {
        return settlementQuote;
    }

    public void setSettlementQuote(Quote settlementQuote) {
        this.settlementQuote = settlementQuote;
    }

    @Override
    public Class<AlipayApsNotifyPaymentResponse> getResponseClass() {
        return AlipayApsNotifyPaymentResponse.class;
    }

}
