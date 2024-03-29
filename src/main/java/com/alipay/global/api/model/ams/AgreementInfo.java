package com.alipay.global.api.model.ams;

public class AgreementInfo {

    /**
     * The unique ID generated by the merchant to initiate an Easy Pay authorization
     */
    private String authState;

    /**
     * The login ID that the user used to register in the wallet
     */
    private String userLoginId;

    /**
     * The login Type
     */
    private String userLoginType;

    /**
     * The login ID that use to display
     */
    private String displayUserLoginId;

    public String getAuthState() {
        return authState;
    }

    public void setAuthState(String authState) {
        this.authState = authState;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserLoginType() {
        return userLoginType;
    }

    public void setUserLoginType(String userLoginType) {
        this.userLoginType = userLoginType;
    }

    public String getDisplayUserLoginId() {
        return displayUserLoginId;
    }

    public void setDisplayUserLoginId(String displayUserLoginId) {
        this.displayUserLoginId = displayUserLoginId;
    }
}
