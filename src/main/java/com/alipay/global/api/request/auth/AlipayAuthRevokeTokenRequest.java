package com.alipay.global.api.request.auth;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.auth.AlipayAuthRevokeTokenResponse;

public class AlipayAuthRevokeTokenRequest extends AlipayRequest<AlipayAuthRevokeTokenResponse> {

    private String accessToken;
    private String extendInfo;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    @Override
    public Class<AlipayAuthRevokeTokenResponse> getResponseClass() {
        return AlipayAuthRevokeTokenResponse.class;
    }

}
