package com.alipay.global.api.model;

import java.math.BigDecimal;

public class Quote {

    private String     quoteId;
    private String     quoteCurrencyPair;
    private BigDecimal quotePrice;
    private String     quoteStartTime	;
    private String     quoteExpiryTime;
    private Boolean    guaranteed;

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuoteCurrencyPair() {
        return quoteCurrencyPair;
    }

    public void setQuoteCurrencyPair(String quoteCurrencyPair) {
        this.quoteCurrencyPair = quoteCurrencyPair;
    }

    public BigDecimal getQuotePrice() {
        return quotePrice;
    }

    public void setQuotePrice(BigDecimal quotePrice) {
        this.quotePrice = quotePrice;
    }

    public String getQuoteStartTime() {
        return quoteStartTime;
    }

    public void setQuoteStartTime(String quoteStartTime) {
        this.quoteStartTime = quoteStartTime;
    }

    public String getQuoteExpiryTime() {
        return quoteExpiryTime;
    }

    public void setQuoteExpiryTime(String quoteExpiryTime) {
        this.quoteExpiryTime = quoteExpiryTime;
    }

    public Boolean getGuaranteed() {
        return guaranteed;
    }

    public void setGuaranteed(Boolean guaranteed) {
        this.guaranteed = guaranteed;
    }

}
