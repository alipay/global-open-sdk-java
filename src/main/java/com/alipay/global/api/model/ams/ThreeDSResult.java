package com.alipay.global.api.model.ams;


public class ThreeDSResult {

    private String threeDSVersion;

    private String eci;

    private String cavv;

    private String dsTransactionId;

    private String xid;

    public String getThreeDSVersion() {
        return threeDSVersion;
    }

    public void setThreeDSVersion(String threeDSVersion) {
        this.threeDSVersion = threeDSVersion;
    }

    public String getEci() {
        return eci;
    }

    public void setEci(String eci) {
        this.eci = eci;
    }

    public String getCavv() {
        return cavv;
    }

    public void setCavv(String cavv) {
        this.cavv = cavv;
    }

    public String getDsTransactionId() {
        return dsTransactionId;
    }

    public void setDsTransactionId(String dsTransactionId) {
        this.dsTransactionId = dsTransactionId;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }
}
