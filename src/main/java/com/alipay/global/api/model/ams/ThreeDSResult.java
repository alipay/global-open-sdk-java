package com.alipay.global.api.model.ams;


public class ThreeDSResult {

    /**
     * The version of 3D Secure protocol
     */
    private String threeDSVersion;

    /**
     * Electronic Commerce Indicator (ECI) that is returned by the card scheme
     */
    private String eci;

    /**
     * The cardholder authentication value
     */
    private String cavv;

    /**
     * dsTransactionId
     */
    private String dsTransactionId;

    /**
     * The unique transaction identifier assigned by the Directory Server (DS) for 3D Secure authentication
     */
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
