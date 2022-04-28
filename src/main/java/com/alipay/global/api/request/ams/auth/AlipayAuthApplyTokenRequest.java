package com.alipay.global.api.request.ams.auth;

import com.alipay.global.api.model.ams.CustomerBelongsTo;
import com.alipay.global.api.model.ams.GrantType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.auth.AlipayAuthApplyTokenResponse;

public class AlipayAuthApplyTokenRequest extends AlipayRequest<AlipayAuthApplyTokenResponse> {

    private GrantType         grantType;
    private CustomerBelongsTo customerBelongsTo;
    private String            authCode;
    private String            refreshToken;
    private String            extendInfo;
    private String            merchantRegion;

    public String getMerchantRegion() {
        return merchantRegion;
    }

    public void setMerchantRegion(String merchantRegion) {
        this.merchantRegion = merchantRegion;
    }

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
