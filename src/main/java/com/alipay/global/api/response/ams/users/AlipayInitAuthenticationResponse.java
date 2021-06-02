package com.alipay.global.api.response.ams.users;

import com.alipay.global.api.response.AlipayResponse;

public class AlipayInitAuthenticationResponse extends AlipayResponse {

    private String authenticationRequestId;

    public String getAuthenticationRequestId() {
        return authenticationRequestId;
    }

    public void setAuthenticationRequestId(String authenticationRequestId) {
        this.authenticationRequestId = authenticationRequestId;
    }

}
