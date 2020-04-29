package com.alipay.global.api.response.users;

import com.alipay.global.api.response.AlipayResponse;

public class AlipayVerifyAuthenticationResponse extends AlipayResponse {

    private boolean isPassed;

    public boolean getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(boolean isPassed) {
        this.isPassed = isPassed;
    }

}