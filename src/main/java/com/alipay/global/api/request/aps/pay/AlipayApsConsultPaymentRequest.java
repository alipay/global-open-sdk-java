package com.alipay.global.api.request.aps.pay;

import com.alipay.global.api.model.aps.*;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.aps.pay.AlipayApsConsultPaymentResponse;

import java.util.List;

public class AlipayApsConsultPaymentRequest extends AlipayRequest<AlipayApsConsultPaymentResponse> {

    private Amount             paymentAmount;
    private String             userRegion;
    private PaymentFactor      paymentFactor;
    private SettlementStrategy settlementStrategy;
    private List<String>       allowedPspRegions;
    private Merchant           merchant;
    private String             referenceUserId;
    private Env                env;

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
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

    public List<String> getAllowedPspRegions() {
        return allowedPspRegions;
    }

    public void setAllowedPspRegions(List<String> allowedPspRegions) {
        this.allowedPspRegions = allowedPspRegions;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
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

    @Override
    public Class<AlipayApsConsultPaymentResponse> getResponseClass() {
        return AlipayApsConsultPaymentResponse.class;
    }

}
