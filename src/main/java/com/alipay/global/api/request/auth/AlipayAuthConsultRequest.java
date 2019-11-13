package com.alipay.global.api.request.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.global.api.model.CustomerBelongsTo;
import com.alipay.global.api.model.OsType;
import com.alipay.global.api.model.ScopeType;
import com.alipay.global.api.model.TerminalType;
import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.auth.AlipayAuthConsultResponse;

public class AlipayAuthConsultRequest extends AlipayRequest<AlipayAuthConsultResponse> {

    @JSONField(serialize = false)
    private final static String httpMethod =  HttpMethod.POST.name();

    private CustomerBelongsTo customerBelongsTo;
    private String            authClientId;
    private String            authRedirectUrl;
    private ScopeType[]       scopes;
    private String            authState;
    private TerminalType      terminalType;
    private OsType            osType;
    private String            osVersion;
    private String            extendInfo;

    public CustomerBelongsTo getCustomerBelongsTo() {
        return customerBelongsTo;
    }

    public void setCustomerBelongsTo(CustomerBelongsTo customerBelongsTo) {
        this.customerBelongsTo = customerBelongsTo;
    }

    public String getAuthClientId() {
        return authClientId;
    }

    public void setAuthClientId(String authClientId) {
        this.authClientId = authClientId;
    }

    public String getAuthRedirectUrl() {
        return authRedirectUrl;
    }

    public void setAuthRedirectUrl(String authRedirectUrl) {
        this.authRedirectUrl = authRedirectUrl;
    }

    public ScopeType[] getScopes() {
        return scopes;
    }

    public void setScopes(ScopeType[] scopes) {
        this.scopes = scopes;
    }

    public String getAuthState() {
        return authState;
    }

    public void setAuthState(String authState) {
        this.authState = authState;
    }

    public TerminalType getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(TerminalType terminalType) {
        this.terminalType = terminalType;
    }

    public OsType getOsType() {
        return osType;
    }

    public void setOsType(OsType osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    @Override
    public String getHttpMethod() {
        return httpMethod;
    }

    @Override
    public Class<AlipayAuthConsultResponse> getResponseClass() {
        return AlipayAuthConsultResponse.class;
    }

}
