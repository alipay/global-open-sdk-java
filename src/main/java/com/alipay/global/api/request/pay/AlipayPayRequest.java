package com.alipay.global.api.request.pay;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.pay.AlipayPayResponse;
import com.alipay.global.api.model.*;

public class AlipayPayRequest extends AlipayRequest<AlipayPayResponse> {

    private ProductCodeType         productCode;
    private String                  paymentRequestId;
    private Order                   order;
    private Amount                  paymentAmount;
    private PaymentMethod           payToMethod;
    private PaymentMethod           paymentMethod;
    private String                  paymentExpiryTime;
    private String                  paymentRedirectUrl;
    private String                  paymentNotifyUrl;
    private Boolean                 isAuthorization;
    private PaymentVerificationData paymentVerificationData;
    private PaymentFactor           paymentFactor;
    private Merchant                merchant;
    private SettlementStrategy      settlementStrategy;
    private String                  extendInfo;
    private CreditPayPlan           creditPayPlan;

    public ProductCodeType getProductCode() {
        return productCode;
    }

    public void setProductCode(ProductCodeType productCode) {
        this.productCode = productCode;
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

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentMethod getPayToMethod() {
        return payToMethod;
    }

    public void setPayToMethod(PaymentMethod payToMethod) {
        this.payToMethod = payToMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentExpiryTime() {
        return paymentExpiryTime;
    }

    public void setPaymentExpiryTime(String paymentExpiryTime) {
        this.paymentExpiryTime = paymentExpiryTime;
    }

    public String getPaymentRedirectUrl() {
        return paymentRedirectUrl;
    }

    public void setPaymentRedirectUrl(String paymentRedirectUrl) {
        this.paymentRedirectUrl = paymentRedirectUrl;
    }

    public String getPaymentNotifyUrl() {
        return paymentNotifyUrl;
    }

    public void setPaymentNotifyUrl(String paymentNotifyUrl) {
        this.paymentNotifyUrl = paymentNotifyUrl;
    }

    public Boolean getIsAuthorization() {
        return isAuthorization;
    }

    public void setIsAuthorization(Boolean isAuthorization) {
        this.isAuthorization = isAuthorization;
    }

    public PaymentVerificationData getPaymentVerificationData() {
        return paymentVerificationData;
    }

    public void setPaymentVerificationData(PaymentVerificationData paymentVerificationData) {
        this.paymentVerificationData = paymentVerificationData;
    }

    public PaymentFactor getPaymentFactor() {
        return paymentFactor;
    }

    public void setPaymentFactor(PaymentFactor paymentFactor) {
        this.paymentFactor = paymentFactor;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public SettlementStrategy getSettlementStrategy() {
        return settlementStrategy;
    }

    public void setSettlementStrategy(SettlementStrategy settlementStrategy) {
        this.settlementStrategy = settlementStrategy;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    public CreditPayPlan getCreditPayPlan() {
        return creditPayPlan;
    }

    public void setCreditPayPlan(CreditPayPlan creditPayPlan) {
        this.creditPayPlan = creditPayPlan;
    }

    @Override
    public Class<AlipayPayResponse> getResponseClass() {
        return AlipayPayResponse.class;
    }

}
