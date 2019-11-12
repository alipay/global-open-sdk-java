package com.alipay.api.request.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.api.net.HttpMethod;
import com.alipay.api.request.AlipayRequest;
import com.alipay.api.response.auth.AlipayAuthRevokeTokenResponse;

public class AlipayAuthRevokeTokenRequest extends AlipayRequest<AlipayAuthRevokeTokenResponse> {

    @JSONField(serialize = false)
    private final static String httpMethod = HttpMethod.POST.name();

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
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Class<AlipayAuthRevokeTokenResponse> getResponseClass() {
        return AlipayAuthRevokeTokenResponse.class;
    }

}
