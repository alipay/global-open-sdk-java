package com.alipay.global.api.model.aps;

public class PaymentOption {

    private String                       paymentMethodType;
    private PaymentMethodCategoryType    paymentMethodCategory;
    private boolean                      enabled;
    private boolean                      preferred;
    private String                       disableReason;
    private Logo                         logo;
    private PaymentOptionDetail          paymentOptionDetail;

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public PaymentMethodCategoryType getPaymentMethodCategory() {
        return paymentMethodCategory;
    }

    public void setPaymentMethodCategory(PaymentMethodCategoryType paymentMethodCategory) {
        this.paymentMethodCategory = paymentMethodCategory;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    public String getDisableReason() {
        return disableReason;
    }

    public void setDisableReason(String disableReason) {
        this.disableReason = disableReason;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public PaymentOptionDetail getPaymentOptionDetail() {
        return paymentOptionDetail;
    }

    public void setPaymentOptionDetail(PaymentOptionDetail paymentOptionDetail) {
        this.paymentOptionDetail = paymentOptionDetail;
    }
}
