package com.alipay.global.api.model.ams;

public class CardInfo {

    private String        cardNo;

    private String        cardBrand;

    private String        cardToken;

    private String        issuingCountry;

    private String        funding;

    private String        paymentMethodRegion;

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
