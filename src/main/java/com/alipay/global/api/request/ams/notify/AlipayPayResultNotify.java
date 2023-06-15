package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.PspCustomerInfo;
import com.alipay.global.api.model.ams.Quote;

public class AlipayPayResultNotify {

    private String          notifyType;
    private Result          resultInfo;
    private String          paymentRequestId;

    private String          paymentId;

    private Amount          paymentAmount;

    private String          paymentCreateTime;

    private String          paymentTime;

    private Amount          customsDeclarationAmount;

    private Amount          grossSettlementAmount;

    private Quote           settlementQuote;

    private PspCustomerInfo pspCustomerInfo;

    private String          acquirerReferenceNo;

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

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

    public PspCustomerInfo getPspCustomerInfo() {
        return pspCustomerInfo;
    }

    public void setPspCustomerInfo(PspCustomerInfo pspCustomerInfo) {
        this.pspCustomerInfo = pspCustomerInfo;
    }

    public String getAcquirerReferenceNo() {
        return acquirerReferenceNo;
    }

    public void setAcquirerReferenceNo(String acquirerReferenceNo) {
        this.acquirerReferenceNo = acquirerReferenceNo;
    }

    public Result getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(Result resultInfo) {
        this.resultInfo = resultInfo;
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

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Amount paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentCreateTime() {
        return paymentCreateTime;
    }

    public void setPaymentCreateTime(String paymentCreateTime) {
        this.paymentCreateTime = paymentCreateTime;
    }
}
