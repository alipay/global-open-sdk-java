package com.alipay.global.api.model.aps;

public class Merchant {


    private String  referenceMerchantId;
    private String  merchantMCC;
    private String  merchantName;
    private Address merchantAddress;
    private String  merchantDisplayName;
    private String  merchantRegisterDate;
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

    public Address getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(Address merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getMerchantDisplayName() {
        return merchantDisplayName;
    }

    public void setMerchantDisplayName(String merchantDisplayName) {
        this.merchantDisplayName = merchantDisplayName;
    }

    public String getMerchantRegisterDate() {
        return merchantRegisterDate;
    }

    public void setMerchantRegisterDate(String merchantRegisterDate) {
        this.merchantRegisterDate = merchantRegisterDate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

}
