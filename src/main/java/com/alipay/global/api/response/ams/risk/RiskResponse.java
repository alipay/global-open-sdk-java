/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.response.ams.risk;

import com.alipay.global.api.response.AlipayResponse;

public class RiskResponse extends AlipayResponse {

    private String securityId;

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }
}