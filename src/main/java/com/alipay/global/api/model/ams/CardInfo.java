package com.alipay.global.api.model.ams;

public class CardInfo {

    /**
     * The masked card number, which just shows part of the card number and can be used to display to the user
     */
    private String        cardNo;

    /**
     * The card brand
     */
    private String        cardBrand;

    /**
     * The token of the card
     */
    private String        cardToken;

    /**
     * The issuing country of the card
     */
    private String        issuingCountry;

    /**
     * The funding type of the card
     */
    private String        funding;

    /**
     * The region code that represents the country or region of the payment method
     */
    private String        paymentMethodRegion;

    /**
     * The result of 3D Secure authentication
     */
    private ThreeDSResult threeDSResult;

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

    public ThreeDSResult getThreeDSResult() {
        return threeDSResult;
    }

    public void setThreeDSResult(ThreeDSResult threeDSResult) {
        this.threeDSResult = threeDSResult;
    }
}
