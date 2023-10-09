package com.alipay.global.api.model.ams;

public class RiskThreeDSResult {

    /**
     * The version of 3D Secure protocol
     */
    private String threeDSVersion;

    /**
     * Indicates the type of user interactions during 3DS 2.0 authentication
     */
    private String threeDSInteractionMode;

    /**
     * Electronic Commerce Indicator (ECI) that is returned by the card scheme
     */
    private String eci;

    /**
     * The cardholder authentication value
     */
    private String cavv;

    public String getThreeDSVersion() {
        return threeDSVersion;
    }

    public void setThreeDSVersion(String threeDSVersion) {
        this.threeDSVersion = threeDSVersion;
    }

    public String getThreeDSInteractionMode() {
        return threeDSInteractionMode;
    }

    public void setThreeDSInteractionMode(String threeDSInteractionMode) {
        this.threeDSInteractionMode = threeDSInteractionMode;
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
}