package com.alipay.global.api.response.pay;

import com.alipay.global.api.model.*;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayPayQueryResponse extends AlipayResponse {

    private TransactionStatusType paymentStatus;
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
    private String                extendInfo;

    public TransactionStatusType getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(TransactionStatusType paymentStatus) {
        this.paymentStatus = paymentStatus;
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
}
