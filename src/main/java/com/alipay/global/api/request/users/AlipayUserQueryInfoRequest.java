package com.alipay.global.api.request.users;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.users.AlipayUserQueryInfoResponse;

public class AlipayUserQueryInfoRequest extends AlipayRequest<AlipayUserQueryInfoResponse> {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Class<AlipayUserQueryInfoResponse> getResponseClass() {
        return AlipayUserQueryInfoResponse.class;
    }

}
