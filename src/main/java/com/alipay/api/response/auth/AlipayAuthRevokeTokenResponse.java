package com.alipay.api.response.auth;

import com.alipay.api.response.AlipayResponse;

public class AlipayAuthRevokeTokenResponse extends AlipayResponse {

    private String extendInfo;

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

}
