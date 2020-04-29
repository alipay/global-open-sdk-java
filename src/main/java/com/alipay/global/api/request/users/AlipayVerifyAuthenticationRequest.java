package com.alipay.global.api.request.users;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.global.api.model.AuthenticationType;
import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.users.AlipayVerifyAuthenticationResponse;

public class AlipayVerifyAuthenticationRequest extends AlipayRequest<AlipayVerifyAuthenticationResponse> {

    @JSONField(serialize = false)
    private final static String httpMethod = HttpMethod.POST.name();

    private AuthenticationType authenticationType;
    private String             authenticationRequestId;
    private String             authenticationValue;

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public String getAuthenticationRequestId() {
        return authenticationRequestId;
    }

    public void setAuthenticationRequestId(String authenticationRequestId) {
        this.authenticationRequestId = authenticationRequestId;
    }

    public String getAuthenticationValue() {
        return authenticationValue;
    }

    public void setAuthenticationValue(String authenticationValue) {
        this.authenticationValue = authenticationValue;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Class<AlipayVerifyAuthenticationResponse> getResponseClass() {
        return AlipayVerifyAuthenticationResponse.class;
    }

}
