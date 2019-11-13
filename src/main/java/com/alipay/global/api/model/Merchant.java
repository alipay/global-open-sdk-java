package com.alipay.global.api.model;

import java.util.Date;

public class Merchant {
    private String  referenceMerchantId;
    private String  merchantMCC;
    private String  merchantName;
    private String  merchantDisplayName;
    private Address	merchantAddress;
    private Date    merchantRegisterDate;
    private Store   store;

    public String getReferenceMerchantId() {
        return referenceMerchantId;
    }

    public void setReferenceMerchantId(String referenceMerchantId) {
        this.referenceMerchantId = referenceMerchantId;
    }

    public String getMerchantMCC() {
        return merchantMCC;
    }

    public void setMerchantMCC(String merchantMCC) {
        this.merchantMCC = merchantMCC;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantDisplayName() {
        return merchantDisplayName;
    }

    public void setMerchantDisplayName(String merchantDisplayName) {
        this.merchantDisplayName = merchantDisplayName;
    }

    public Address getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(Address merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public Date getMerchantRegisterDate() {
        return merchantRegisterDate;
    }

    public void setMerchantRegisterDate(Date merchantRegisterDate) {
        this.merchantRegisterDate = merchantRegisterDate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
