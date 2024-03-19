package com.alipay.global.api.response.ams.subscription;

import com.alipay.global.api.response.AlipayResponse;

/**
 * @Author yanhong
 * @version $Id: AlipaySubscriptionCreateResponse.java, v 0.1 2024年03月19日 3:37 PM yanhong Exp $
 **/
public class AlipaySubscriptionCreateResponse extends AlipayResponse {

    private String schemeUrl;

    private String applinkUrl;

    private String normalUrl;

    private String appIdentifier;

    public String getSchemeUrl() {
        return schemeUrl;
    }

    public void setSchemeUrl(String schemeUrl) {
        this.schemeUrl = schemeUrl;
    }

    public String getApplinkUrl() {
        return applinkUrl;
    }

    public void setApplinkUrl(String applinkUrl) {
        this.applinkUrl = applinkUrl;
    }

    public String getNormalUrl() {
        return normalUrl;
    }

    public void setNormalUrl(String normalUrl) {
        this.normalUrl = normalUrl;
    }

    public String getAppIdentifier() {
        return appIdentifier;
    }

    public void setAppIdentifier(String appIdentifier) {
        this.appIdentifier = appIdentifier;
    }
}
