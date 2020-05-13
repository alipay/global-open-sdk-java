package com.alipay.global.api.request.users;

import com.alipay.global.api.model.AuthenticationChannelType;
import com.alipay.global.api.model.AuthenticationType;
import com.alipay.global.api.model.UserIdentityType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.users.AlipayInitAuthenticationResponse;

public class AlipayInitAuthenticationRequest extends AlipayRequest<AlipayInitAuthenticationResponse> {

    private AuthenticationType        authenticationType;
    private String                    authenticationRequestId;
    private AuthenticationChannelType authenticationChannelType;
    private UserIdentityType          userIdentityType;
    private String                    userIdentityValue;

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

    public AuthenticationChannelType getAuthenticationChannelType() {
        return authenticationChannelType;
    }

    public void setAuthenticationChannelType(AuthenticationChannelType authenticationChannelType) {
        this.authenticationChannelType = authenticationChannelType;
    }

    public UserIdentityType getUserIdentityType() {
        return userIdentityType;
    }

    public void setUserIdentityType(UserIdentityType userIdentityType) {
        this.userIdentityType = userIdentityType;
    }

    public String getUserIdentityValue() {
        return userIdentityValue;
    }

    public void setUserIdentityValue(String userIdentityValue) {
        this.userIdentityValue = userIdentityValue;
    }

    @Override
    public Class<AlipayInitAuthenticationResponse> getResponseClass() {
        return AlipayInitAuthenticationResponse.class;
    }

}