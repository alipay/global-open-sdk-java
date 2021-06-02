package com.alipay.global.api.response.aps.pay;

import com.alipay.global.api.model.aps.*;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayApsUserInitiatedPayResponse extends AlipayResponse {

    private CodeType           codeType;
    private String             paymentRequestId;
    private Order              order;
    private PaymentFactor      paymentFactor;
    private Amount             amount;
    private String             paymentNotifyUrl;
    private String             paymentRedirectUrl;
    private String             paymentExpiryTime;
    private SettlementStrategy settlementStrategy;

    public CodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentFactor getPaymentFactor() {
        return paymentFactor;
    }

    public void setPaymentFactor(PaymentFactor paymentFactor) {
        this.paymentFactor = paymentFactor;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getPaymentNotifyUrl() {
        return paymentNotifyUrl;
    }

    public void setPaymentNotifyUrl(String paymentNotifyUrl) {
        this.paymentNotifyUrl = paymentNotifyUrl;
    }

    public String getPaymentRedirectUrl() {
        return paymentRedirectUrl;
    }

    public void setPaymentRedirectUrl(String paymentRedirectUrl) {
        this.paymentRedirectUrl = paymentRedirectUrl;
    }

    public String getPaymentExpiryTime() {
        return paymentExpiryTime;
    }

    public void setPaymentExpiryTime(String paymentExpiryTime) {
        this.paymentExpiryTime = paymentExpiryTime;
    }

    public SettlementStrategy getSettlementStrategy() {
        return settlementStrategy;
    }

    public void setSettlementStrategy(SettlementStrategy settlementStrategy) {
        this.settlementStrategy = settlementStrategy;
    }

}

