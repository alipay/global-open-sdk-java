package com.alipay.global.api.response.pay;

import com.alipay.global.api.model.*;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayPayResponse extends AlipayResponse {

    private String              paymentRequestId;
    private String              paymentId;
    private Amount              paymentAmount;
    private Amount              actualPaymentAmount;
    private Quote               paymentQuote;
    private String              paymentTime;
    private String              paymentCreateTime;
    private String              authExpiryTime;
    private Amount              nonGuaranteeCouponValue;
    private PspCustomerInfo     pspCustomerInfo;
    private ChallengeActionForm challengeActionForm;
    private RedirectActionForm  redirectActionForm;
    private OrderCodeForm       orderCodeForm;
    private String              extendInfo;

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

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentCreateTime() {
        return paymentCreateTime;
    }

    public void setPaymentCreateTime(String paymentCreateTime) {
        this.paymentCreateTime = paymentCreateTime;
    }

    public String getAuthExpiryTime() {
        return authExpiryTime;
    }

    public void setAuthExpiryTime(String authExpiryTime) {
        this.authExpiryTime = authExpiryTime;
    }

    public Amount getNonGuaranteeCouponValue() {
        return nonGuaranteeCouponValue;
    }

    public void setNonGuaranteeCouponValue(Amount nonGuaranteeCouponValue) {
        this.nonGuaranteeCouponValue = nonGuaranteeCouponValue;
    }

    public PspCustomerInfo getPspCustomerInfo() {
        return pspCustomerInfo;
    }

    public void setPspCustomerInfo(PspCustomerInfo pspCustomerInfo) {
        this.pspCustomerInfo = pspCustomerInfo;
    }

    public ChallengeActionForm getChallengeActionForm() {
        return challengeActionForm;
    }

    public void setChallengeActionForm(ChallengeActionForm challengeActionForm) {
        this.challengeActionForm = challengeActionForm;
    }

    public RedirectActionForm getRedirectActionForm() {
        return redirectActionForm;
    }

    public void setRedirectActionForm(RedirectActionForm redirectActionForm) {
        this.redirectActionForm = redirectActionForm;
    }

    public OrderCodeForm getOrderCodeForm() {
        return orderCodeForm;
    }

    public void setOrderCodeForm(OrderCodeForm orderCodeForm) {
        this.orderCodeForm = orderCodeForm;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }
}
