package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPayResponse;

public class AlipayPayRequest extends AlipayRequest<AlipayPayResponse> {

    /**
     * Represents the payment product that is being used.
     */
    private ProductCodeType         productCode;

    /**
     * The unique ID assigned by a merchant to identify a payment request
     */
    private String                  paymentRequestId;

    /**
     * The order information
     */
    private Order                   order;

    /**
     * The payment amount that the merchant requests to receive in the order currency
     */
    private Amount                  paymentAmount;

    /**
     * The payment method that is used to collect the payment by the merchant or acquirer
     */
    private PaymentMethod           paymentMethod;

    /**
     * The specific time after which the payment will expire
     */
    private String                  paymentExpiryTime;

    /**
     * The merchant page URL that the user is redirected to after the payment is completed
     */
    private String                  paymentRedirectUrl;

    /**
     * The URL that is used to receive the payment result notification
     */
    private String                  paymentNotifyUrl;

    /**
     * Factors that impact the payment. This field is used to indicate the payment scenario
     */
    private PaymentFactor           paymentFactor;

    /**
     * The settlement strategy for the payment request.
     */
    private SettlementStrategy      settlementStrategy;

    /**
     * The credit payment plan information for this payment
     */
    private CreditPayPlan           creditPayPlan;

    /**
     * The unique ID that is assigned by Alipay to identify the mini program
     */
    private String                  appId;

    /**
     * The country or region where the merchant operates the business
     */
    private String                  merchantRegion;

    private PaymentMethod           payToMethod;

    private Boolean                 isAuthorization;

    private Merchant                merchant;

    private PaymentVerificationData paymentVerificationData;

    private String                  extendInfo;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantRegion() {
        return merchantRegion;
    }

    public void setMerchantRegion(String merchantRegion) {
        this.merchantRegion = merchantRegion;
    }

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
