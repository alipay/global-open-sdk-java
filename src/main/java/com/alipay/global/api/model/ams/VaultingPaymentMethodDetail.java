package com.alipay.global.api.model.ams;

public class VaultingPaymentMethodDetail {
    private String paymentMethodType;
    private VaultingCard card;

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public VaultingCard getCard() {
        return card;
    }

    public void setCard(VaultingCard card) {
        this.card = card;
    }
}