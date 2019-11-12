package com.alipay.api.model;

public class Store {

    private String  referenceStoreId;
    private String  storeName;
    private String  storeMCC;
    private String  storeDisplayName;
    private String  storeTerminalId;
    private String  storeOperatorId;
    private Address storeAddress;
    private String  storePhoneNo;

    public String getReferenceStoreId() {
        return referenceStoreId;
    }

    public void setReferenceStoreId(String referenceStoreId) {
        this.referenceStoreId = referenceStoreId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreMCC() {
        return storeMCC;
    }

    public void setStoreMCC(String storeMCC) {
        this.storeMCC = storeMCC;
    }

    public String getStoreDisplayName() {
        return storeDisplayName;
    }

    public void setStoreDisplayName(String storeDisplayName) {
        this.storeDisplayName = storeDisplayName;
    }

    public String getStoreTerminalId() {
        return storeTerminalId;
    }

    public void setStoreTerminalId(String storeTerminalId) {
        this.storeTerminalId = storeTerminalId;
    }

    public String getStoreOperatorId() {
        return storeOperatorId;
    }

    public void setStoreOperatorId(String storeOperatorId) {
        this.storeOperatorId = storeOperatorId;
    }

    public Address getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(Address storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStorePhoneNo() {
        return storePhoneNo;
    }

    public void setStorePhoneNo(String storePhoneNo) {
        this.storePhoneNo = storePhoneNo;
    }

}
