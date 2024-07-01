package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.AgreementInfo;
import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.CreditPayPlan;
import com.alipay.global.api.model.ams.Env;
import com.alipay.global.api.model.ams.Order;
import com.alipay.global.api.model.ams.PaymentFactor;
import com.alipay.global.api.model.ams.PaymentMethod;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.model.ams.RiskData;
import com.alipay.global.api.model.ams.SettlementStrategy;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayPaymentSessionResponse;

import java.util.List;

public class AlipayPaymentSessionRequest extends AlipayRequest<AlipayPaymentSessionResponse> {

    /**
     * Represents the payment product that is being used. The fixed value is CASHIER_PAYMENT
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
     *  The exipry time of paymentSession
     */
    private String                  paymentSessionExpiryTime;

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
     * The settlement strategy for the payment request
     */
    private SettlementStrategy      settlementStrategy;

    /**
     * The credit payment plan information for this payment
     */
    private CreditPayPlan           creditPayPlan;

    /**
     * The country or region where the merchant operates the business
     */
    private String                  merchantRegion;

    /**
     * Information about the environment where the order is placed
     */
    private Env                     env;

    /**
     * Authorization information about Easy Pay payments
     */
    private AgreementInfo           agreementInfo;

    /**
     * The data used by Ant Group for risk control purposes.
     */
    private RiskData                riskData;

    /**
     * This param is used for Easy pay payments,set its value to EASY_PAY
     */
    private String                  productScene;

    private List<PaymentMethod> savedPaymentMethods;

    private String locale;

    @Override
    public Class<AlipayPaymentSessionResponse> getResponseClass() {
        return AlipayPaymentSessionResponse.class;
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentSessionExpiryTime() {
        return paymentSessionExpiryTime;
    }

    public void setPaymentSessionExpiryTime(String paymentSessionExpiryTime) {
        this.paymentSessionExpiryTime = paymentSessionExpiryTime;
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

    public CreditPayPlan getCreditPayPlan() {
        return creditPayPlan;
    }

    public void setCreditPayPlan(CreditPayPlan creditPayPlan) {
        this.creditPayPlan = creditPayPlan;
    }

    public String getMerchantRegion() {
        return merchantRegion;
    }

    public void setMerchantRegion(String merchantRegion) {
        this.merchantRegion = merchantRegion;
    }

    public Env getEnv() {
        return env;
    }

    public void setEnv(Env env) {
        this.env = env;
    }

    public AgreementInfo getAgreementInfo() {
        return agreementInfo;
    }

    public void setAgreementInfo(AgreementInfo agreementInfo) {
        this.agreementInfo = agreementInfo;
    }

    public RiskData getRiskData() {
        return riskData;
    }

    public void setRiskData(RiskData riskData) {
        this.riskData = riskData;
    }

    public String getProductScene() {
        return productScene;
    }

    public void setProductScene(String productScene) {
        this.productScene = productScene;
    }

    public List<PaymentMethod> getSavedPaymentMethods() {
        return savedPaymentMethods;
    }

    public void setSavedPaymentMethods(List<PaymentMethod> savedPaymentMethods) {
        this.savedPaymentMethods = savedPaymentMethods;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
