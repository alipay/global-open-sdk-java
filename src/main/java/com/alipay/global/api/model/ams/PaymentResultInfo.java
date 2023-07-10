package com.alipay.global.api.model.ams;

public class PaymentResultInfo {

    /**
     * The masked card number, which just shows part of the card number and can be used to display to the user
     */
    private String cardNo;

    /**
     * The card brand, which can be used to display to the user
     */
    private String cardBrand;

    /**
     * The token of the card, the value of this parameter is used by paymentMethodId in the pay
     */
    private String cardToken;

    /**
     * The issuing country of the card
     */
    private String issuingCountry;

    /**
     * The funding type of the card.
     */
    private String funding;

    /**
     * The region code that represents the country or region of the payment method
     */
    private String paymentMethodRegion;

    /**
     * The raw AVS result. See AVS result codes to check the valid values
     */
    private String avsResultRaw;

    /**
     * The raw Card Verification Value (CVV), Card Security Code (CSC), or Card Verification Code (CVC) result
     */
    private String cvvResultRaw;

    /**
     * The unique ID assigned by the card scheme to identify a transaction. The value of this parameter is used by the same parameter of pay (Cashier Payment) request in subsequent payments
     */
    private String networkTransactionId;

    public String getAvsResultRaw() {
        return avsResultRaw;
    }

    public void setAvsResultRaw(String avsResultRaw) {
        this.avsResultRaw = avsResultRaw;
    }

    public String getCvvResultRaw() {
        return cvvResultRaw;
    }

    public void setCvvResultRaw(String cvvResultRaw) {
        this.cvvResultRaw = cvvResultRaw;
    }

    public String getNetworkTransactionId() {
        return networkTransactionId;
    }

    public void setNetworkTransactionId(String networkTransactionId) {
        this.networkTransactionId = networkTransactionId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public String getPaymentMethodRegion() {
        return paymentMethodRegion;
    }

    public void setPaymentMethodRegion(String paymentMethodRegion) {
        this.paymentMethodRegion = paymentMethodRegion;
    }
}
