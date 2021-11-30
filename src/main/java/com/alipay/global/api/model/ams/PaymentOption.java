package com.alipay.global.api.model.ams;

import com.alipay.global.api.model.aps.Logo;

import java.util.List;
import java.util.Map;

public class PaymentOption {

    private String                       paymentMethodType;
    private PaymentMethodCategoryType    paymentMethodCategory;
    private boolean                      enabled;
    private boolean                      preferred;
    private String                       disableReason;
    private Map<String, AmountLimitInfo> amountLimitInfoMap;
    private List<String>                 supportedCurrencies;
    private String                       paymentOptionDetail;
    private String                       extendInfo;
    private Logo                         logo;
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

    public Map<String, AmountLimitInfo> getAmountLimitInfoMap() {
        return amountLimitInfoMap;
    }

    public void setAmountLimitInfoMap(Map<String, AmountLimitInfo> amountLimitInfoMap) {
        this.amountLimitInfoMap = amountLimitInfoMap;
    }

    public List<String> getSupportedCurrencies() {
        return supportedCurrencies;
    }

    public void setSupportedCurrencies(List<String> supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
    }

    public String getPaymentOptionDetail() {
        return paymentOptionDetail;
    }

    public void setPaymentOptionDetail(String paymentOptionDetail) {
        this.paymentOptionDetail = paymentOptionDetail;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }
}
