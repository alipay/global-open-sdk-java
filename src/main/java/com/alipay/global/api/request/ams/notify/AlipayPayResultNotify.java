package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.PaymentResultInfo;
import com.alipay.global.api.model.ams.PspCustomerInfo;
import com.alipay.global.api.model.ams.Quote;

public class AlipayPayResultNotify extends AlipayNotify{

    /**
     * The unique ID that is assigned by a merchant to identify a payment request.
     */
    private String          paymentRequestId;

    /**
     * unique id generated from ipay for this payment
     */
    private String          paymentId;

    /**
     * amount of this payment
     */
    private Amount          paymentAmount;

    /**
     * create time of this payment
     */
    private String          paymentCreateTime;

    /**
     * the time of payment finish
     */
    private String          paymentTime;

    /**
     * The total amount for customs declaration
     */
    private Amount          customsDeclarationAmount;

    /**
     * The value of this field equals to transaction amount multiplied by the value of settlementQuote. This field is returned when the currency exchange is predetermined and the exchange rate is locked at the time of transaction
     */
    private Amount          grossSettlementAmount;

    /**
     * The exchange rate between the settlement currency and transaction currency. This field is returned when grossSettlementAmount is returned
     */
    private Quote           settlementQuote;

    /**
     * Information about the customer of Alipay+ Mobile Payment Provider (Alipay+ MPP)
     */
    private PspCustomerInfo pspCustomerInfo;

    /**
     * The unique ID assigned by the non-Alipay acquirer for the transaction
     */
    private String          acquirerReferenceNo;

    /**
     * The payment result information
     */
    private PaymentResultInfo paymentResultInfo;

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

    public PaymentResultInfo getPaymentResultInfo() {
        return paymentResultInfo;
    }

    public void setPaymentResultInfo(PaymentResultInfo paymentResultInfo) {
        this.paymentResultInfo = paymentResultInfo;
    }
}
