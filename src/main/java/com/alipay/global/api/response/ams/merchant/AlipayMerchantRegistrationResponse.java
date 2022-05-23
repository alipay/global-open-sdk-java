package com.alipay.global.api.response.ams.merchant;

import com.alipay.global.api.response.AlipayResponse;


public class AlipayMerchantRegistrationResponse extends AlipayResponse {

    private String passThroughInfo;

    public String getPassThroughInfo() {
        return passThroughInfo;
    }

    public void setPassThroughInfo(String passThroughInfo) {
        this.passThroughInfo = passThroughInfo;
    }
}
