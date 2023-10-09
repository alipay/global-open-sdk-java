package com.alipay.global.api.model.ams;


public class RiskOrder {
    /**
     * The order type
     */
    private String orderType;

    /**
     * The webpage where the buyer accessed the merchant.
     */
    private String referringSite;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getReferringSite() {
        return referringSite;
    }

    public void setReferringSite(String referringSite) {
        this.referringSite = referringSite;
    }
}