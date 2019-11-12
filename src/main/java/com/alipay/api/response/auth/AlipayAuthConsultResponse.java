package com.alipay.api.response.auth;

import com.alipay.api.response.AlipayResponse;

public class AlipayAuthConsultResponse extends AlipayResponse {

    private String authUrl;
    private String extendInfo;

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

}

