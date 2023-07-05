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
    private BrowserInfo  browserInfo;
    private String       colorDepth;
    private String       screenHeight;
    private String       screenWidth;
    private Integer      timeZoneOffset;
    private String       deviceBrand;
    private String       deviceModel;
    private String       deviceLanguage;
    private String       deviceId;


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

    public BrowserInfo getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(BrowserInfo browserInfo) {
        this.browserInfo = browserInfo;
    }

    public String getColorDepth() {
        return colorDepth;
    }

    public void setColorDepth(String colorDepth) {
        this.colorDepth = colorDepth;
    }

    public String getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(String screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(String screenWidth) {
        this.screenWidth = screenWidth;
    }

    public Integer getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public void setTimeZoneOffset(Integer timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceLanguage() {
        return deviceLanguage;
    }

    public void setDeviceLanguage(String deviceLanguage) {
        this.deviceLanguage = deviceLanguage;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
