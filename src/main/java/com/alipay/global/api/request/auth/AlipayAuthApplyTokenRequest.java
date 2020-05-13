package com.alipay.global.api.request.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.global.api.model.CustomerBelongsTo;
import com.alipay.global.api.model.GrantType;
import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.auth.AlipayAuthApplyTokenResponse;

public class AlipayAuthApplyTokenRequest extends AlipayRequest<AlipayAuthApplyTokenResponse> {

    private GrantType         grantType;
    private CustomerBelongsTo customerBelongsTo;
    private String            authCode;
    private String            refreshToken;
    private String            extendInfo;

    public GrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(GrantType grantType) {
        this.grantType = grantType;
    }

    public CustomerBelongsTo getCustomerBelongsTo() {
        return customerBelongsTo;
    }

    public void setCustomerBelongsTo(CustomerBelongsTo customerBelongsTo) {
        this.customerBelongsTo = customerBelongsTo;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    @Override
    public Class<AlipayAuthApplyTokenResponse> getResponseClass() {
        return AlipayAuthApplyTokenResponse.class;
    }

}
