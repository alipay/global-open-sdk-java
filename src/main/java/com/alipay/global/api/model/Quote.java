package com.alipay.global.api.model;

import java.math.BigDecimal;
import java.util.Date;

public class Quote {

    private String     quoteId;
    private String     quoteCurrencyPair;
    private BigDecimal quotePrice;
    private Date       quoteStartTime	;
    private Date       quoteExpiryTime;
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

    public Date getQuoteStartTime() {
        return quoteStartTime;
    }

    public void setQuoteStartTime(Date quoteStartTime) {
        this.quoteStartTime = quoteStartTime;
    }

    public Date getQuoteExpiryTime() {
        return quoteExpiryTime;
    }

    public void setQuoteExpiryTime(Date quoteExpiryTime) {
        this.quoteExpiryTime = quoteExpiryTime;
    }

    public Boolean getGuaranteed() {
        return guaranteed;
    }

    public void setGuaranteed(Boolean guaranteed) {
        this.guaranteed = guaranteed;
    }

}
