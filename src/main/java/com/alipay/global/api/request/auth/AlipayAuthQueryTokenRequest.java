package com.alipay.global.api.request.auth;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.auth.AlipayAuthQueryTokenResponse;

public class AlipayAuthQueryTokenRequest extends AlipayRequest<AlipayAuthQueryTokenResponse> {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Class<AlipayAuthQueryTokenResponse> getResponseClass() {
        return AlipayAuthQueryTokenResponse.class;
    }

}
