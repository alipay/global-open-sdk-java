package com.alipay.global.api.response.ams.auth;

import com.alipay.global.api.response.AlipayResponse;

public class AlipayAuthApplyTokenResponse extends AlipayResponse {

    private String   accessToken;
    private String   accessTokenExpiryTime;
    private String   refreshToken;
    private String   refreshTokenExpiryTime;
    private String   extendInfo;
    private String   userLoginId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenExpiryTime() {
        return accessTokenExpiryTime;
    }

    public void setAccessTokenExpiryTime(String accessTokenExpiryTime) {
        this.accessTokenExpiryTime = accessTokenExpiryTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenExpiryTime() {
        return refreshTokenExpiryTime;
    }

    public void setRefreshTokenExpiryTime(String refreshTokenExpiryTime) {
        this.refreshTokenExpiryTime = refreshTokenExpiryTime;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }
}
