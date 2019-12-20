package com.alipay.global.api.request.users;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.users.AlipayUserQueryInfoResponse;

public class AlipayUserQueryInfoRequest extends AlipayRequest<AlipayUserQueryInfoResponse> {

    @JSONField(serialize = false)
    private final static String httpMethod = HttpMethod.POST.name();

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Class<AlipayUserQueryInfoResponse> getResponseClass() {
        return AlipayUserQueryInfoResponse.class;
    }

}
