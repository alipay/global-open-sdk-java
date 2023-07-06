package com.alipay.global.api.model.ams;

public class BrowserInfo {

    /**
     * The accept header of the user's browser
     */
    private String acceptHeader;

    /**
     * Indicates whether the user's browser is able to run Java
     */
    private Boolean javaEnabled;

    /**
     * Indicates whether the user's browser is able to run Java
     */
    private Boolean javaScriptEnabled;

    /**
     * The language of the user's browser
     */
    private String language;

    /**
     * The user agent of the user's browser
     */
    private String userAgent;

    public String getAcceptHeader() {
        return acceptHeader;
    }

    public void setAcceptHeader(String acceptHeader) {
        this.acceptHeader = acceptHeader;
    }

    public Boolean getJavaEnabled() {
        return javaEnabled;
    }

    public void setJavaEnabled(Boolean javaEnabled) {
        this.javaEnabled = javaEnabled;
    }

    public Boolean getJavaScriptEnabled() {
        return javaScriptEnabled;
    }

    public void setJavaScriptEnabled(Boolean javaScriptEnabled) {
        this.javaScriptEnabled = javaScriptEnabled;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
