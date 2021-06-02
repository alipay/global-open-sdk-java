package com.alipay.global.api.request.ams.users;

import com.alipay.global.api.model.ams.AuthenticationType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.users.AlipayVerifyAuthenticationResponse;

public class AlipayVerifyAuthenticationRequest extends AlipayRequest<AlipayVerifyAuthenticationResponse> {

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
    public Class<AlipayVerifyAuthenticationResponse> getResponseClass() {
        return AlipayVerifyAuthenticationResponse.class;
    }

}
