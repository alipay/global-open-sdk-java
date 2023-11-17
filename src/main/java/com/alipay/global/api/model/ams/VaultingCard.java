package com.alipay.global.api.model.ams;

public class VaultingCard {
    private String cardToken;

    private String lastFour;

    private String brand;

    private String expiryMonth;

    private String expiryYear;

    private Address billingAddress;

    private String avsResultRaw;

    private String cvvResultRaw;

    private String issuingCountry;

    private String funding;

    private String bin;

    private String issuerName;

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    public String getLastFour() {
        return lastFour;
    }

    public void setLastFour(String lastFour) {
        this.lastFour = lastFour;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

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

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }
}