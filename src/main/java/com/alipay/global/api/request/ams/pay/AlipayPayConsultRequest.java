package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayConsultResponse;

import java.util.List;

public class AlipayPayConsultRequest extends AlipayRequest<AlipayPayConsultResponse> {


    private ProductCodeType productCode;
    private Amount          paymentAmount;
    private String          merchantRegion;
    private List<String>    allowedPaymentMethodRegions;
    private List<String>    allowedPaymentMethods;
    private List<String>    blockedPaymentMethods;
    private String          region;
    private String          customerId;
    private String          referenceUserId;
    private Env             env;
    private String          extendInfo;
    private String          userRegion;
    private PaymentFactor   paymentFactor;
    private SettlementStrategy settlementStrategy;
    private Merchant        merchant;
    private List<String>    allowedPspRegions;


    public ProductCodeType getProductCode() {
        return productCode;
    }

    public void setProductCode(ProductCodeType productCode) {
        this.productCode = productCode;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public List<String> getAllowedPaymentMethods() {
        return allowedPaymentMethods;
    }

    public void setAllowedPaymentMethods(List<String> allowedPaymentMethods) {
        this.allowedPaymentMethods = allowedPaymentMethods;
    }

    public List<String> getBlockedPaymentMethods() {
        return blockedPaymentMethods;
    }

    public void setBlockedPaymentMethods(List<String> blockedPaymentMethods) {
        this.blockedPaymentMethods = blockedPaymentMethods;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getReferenceUserId() {
        return referenceUserId;
    }

    public void setReferenceUserId(String referenceUserId) {
        this.referenceUserId = referenceUserId;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    public String getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(String userRegion) {
        this.userRegion = userRegion;
    }

    public PaymentFactor getPaymentFactor() {
        return paymentFactor;
    }

    public void setPaymentFactor(PaymentFactor paymentFactor) {
        this.paymentFactor = paymentFactor;
    }

    public SettlementStrategy getSettlementStrategy() {
        return settlementStrategy;
    }

    public void setSettlementStrategy(SettlementStrategy settlementStrategy) {
        this.settlementStrategy = settlementStrategy;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public List<String> getAllowedPspRegions() {
        return allowedPspRegions;
    }

    public void setAllowedPspRegions(List<String> allowedPspRegions) {
        this.allowedPspRegions = allowedPspRegions;
    }

    public String getMerchantRegion() {
        return merchantRegion;
    }

    public void setMerchantRegion(String merchantRegion) {
        this.merchantRegion = merchantRegion;
    }

    public List<String> getAllowedPaymentMethodRegions() {
        return allowedPaymentMethodRegions;
    }

    public void setAllowedPaymentMethodRegions(List<String> allowedPaymentMethodRegions) {
        this.allowedPaymentMethodRegions = allowedPaymentMethodRegions;
    }

    @Override
    public Class<AlipayPayConsultResponse> getResponseClass() {
        return AlipayPayConsultResponse.class;
    }

}
