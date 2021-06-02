package com.alipay.global.api.model.ams;

public class Env {

    private TerminalType terminalType;
    private OsType       osType;
    private String       userAgent;
    private String       deviceTokenId;
    private String       clientIp;
    private String       cookieId;
    private String       extendInfo;
    private String       storeTerminalId;
    private String       storeTerminalRequestTime;

    public TerminalType getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(TerminalType terminalType) {
        this.terminalType = terminalType;
    }

    public OsType getOsType() {
        return osType;
    }

    public void setOsType(OsType osType) {
        this.osType = osType;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getDeviceTokenId() {
        return deviceTokenId;
    }

    public void setDeviceTokenId(String deviceTokenId) {
        this.deviceTokenId = deviceTokenId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    public String getStoreTerminalId() {
        return storeTerminalId;
    }

    public void setStoreTerminalId(String storeTerminalId) {
        this.storeTerminalId = storeTerminalId;
    }

    public String getStoreTerminalRequestTime() {
        return storeTerminalRequestTime;
    }

    public void setStoreTerminalRequestTime(String storeTerminalRequestTime) {
        this.storeTerminalRequestTime = storeTerminalRequestTime;
    }
}
