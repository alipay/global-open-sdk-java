package com.alipay.global.api.model;

public class PspCustomerInfo {
    private String pspName;
    private String pspCustomerId;
    private String displayCustomerId;

    public String getPspName() {
        return pspName;
    }

    public void setPspName(String pspName) {
        this.pspName = pspName;
    }

    public String getPspCustomerId() {
        return pspCustomerId;
    }

    public void setPspCustomerId(String pspCustomerId) {
        this.pspCustomerId = pspCustomerId;
    }

    public String getDisplayCustomerId() {
        return displayCustomerId;
    }

    public void setDisplayCustomerId(String displayCustomerId) {
        this.displayCustomerId = displayCustomerId;
    }
}
