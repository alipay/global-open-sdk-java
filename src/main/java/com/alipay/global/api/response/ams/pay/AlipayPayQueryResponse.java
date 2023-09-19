package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.*;
import com.alipay.global.api.response.AlipayResponse;

import java.util.List;

public class AlipayPayQueryResponse extends AlipayResponse {

    private TransactionStatusType paymentStatus;
    private String                paymentResultCode;
    private String                paymentResultMessage;
    private String                paymentRequestId;
    private String                paymentId;
    private String                authPaymentId;
    private Amount                paymentAmount;
    private Amount                actualPaymentAmount;
    private Quote                 paymentQuote;
    private String                authExpiryTime;
    private String                paymentCreateTime;
    private String                paymentTime;
    private Amount                nonGuaranteeCouponAmount;
    private PspCustomerInfo       pspCustomerInfo;
    private RedirectActionForm    redirectActionForm;

    private CardInfo              cardInfo;

    private String                acquirerReferenceNo;
    private String                extendInfo;
    private List<Transaction>     transactions;
    private Amount                customsDeclarationAmount;
    private Amount                grossSettlementAmount;
    private Quote                 settlementQuote;
    private PaymentResultInfo     paymentResultInfo;

    private AcquirerInfo        acquirerInfo;

    public Amount getCustomsDeclarationAmount() {
        return customsDeclarationAmount;
    }

    public void setCustomsDeclarationAmount(Amount customsDeclarationAmount) {
        this.customsDeclarationAmount = customsDeclarationAmount;
    }

    public Amount getGrossSettlementAmount() {
        return grossSettlementAmount;
    }

    public void setGrossSettlementAmount(Amount grossSettlementAmount) {
        this.grossSettlementAmount = grossSettlementAmount;
    }

    public Quote getSettlementQuote() {
        return settlementQuote;
    }

    public void setSettlementQuote(Quote settlementQuote) {
        this.settlementQuote = settlementQuote;
    }

    public TransactionStatusType getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(TransactionStatusType paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentResultCode() {
        return paymentResultCode;
    }

    public void setPaymentResultCode(String paymentResultCode) {
        this.paymentResultCode = paymentResultCode;
    }

    public String getPaymentResultMessage() {
        return paymentResultMessage;
    }

    public void setPaymentResultMessage(String paymentResultMessage) {
        this.paymentResultMessage = paymentResultMessage;
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

    public String getAuthPaymentId() {
        return authPaymentId;
    }

    public void setAuthPaymentId(String authPaymentId) {
        this.authPaymentId = authPaymentId;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Amount getActualPaymentAmount() {
        return actualPaymentAmount;
    }

    public void setActualPaymentAmount(Amount actualPaymentAmount) {
        this.actualPaymentAmount = actualPaymentAmount;
    }

    public Quote getPaymentQuote() {
        return paymentQuote;
    }

    public void setPaymentQuote(Quote paymentQuote) {
        this.paymentQuote = paymentQuote;
    }

    public String getAuthExpiryTime() {
        return authExpiryTime;
    }

    public void setAuthExpiryTime(String authExpiryTime) {
        this.authExpiryTime = authExpiryTime;
    }

    public String getPaymentCreateTime() {
        return paymentCreateTime;
    }

    public void setPaymentCreateTime(String paymentCreateTime) {
        this.paymentCreateTime = paymentCreateTime;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Amount getNonGuaranteeCouponAmount() {
        return nonGuaranteeCouponAmount;
    }

    public void setNonGuaranteeCouponAmount(Amount nonGuaranteeCouponAmount) {
        this.nonGuaranteeCouponAmount = nonGuaranteeCouponAmount;
    }

    public PspCustomerInfo getPspCustomerInfo() {
        return pspCustomerInfo;
    }

    public void setPspCustomerInfo(PspCustomerInfo pspCustomerInfo) {
        this.pspCustomerInfo = pspCustomerInfo;
    }

    public RedirectActionForm getRedirectActionForm() {
        return redirectActionForm;
    }

    public void setRedirectActionForm(RedirectActionForm redirectActionForm) {
        this.redirectActionForm = redirectActionForm;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public String getAcquirerReferenceNo() {
        return acquirerReferenceNo;
    }

    public void setAcquirerReferenceNo(String acquirerReferenceNo) {
        this.acquirerReferenceNo = acquirerReferenceNo;
    }

    public PaymentResultInfo getPaymentResultInfo() {
        return paymentResultInfo;
    }

    public void setPaymentResultInfo(PaymentResultInfo paymentResultInfo) {
        this.paymentResultInfo = paymentResultInfo;
    }

    public AcquirerInfo getAcquirerInfo() {
        return acquirerInfo;
    }

    public void setAcquirerInfo(AcquirerInfo acquirerInfo) {
        this.acquirerInfo = acquirerInfo;
    }
}
