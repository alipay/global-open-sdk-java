package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayVaultingSessionResponse;

public class AlipayVaultingSessionRequest extends AlipayRequest<AlipayVaultingSessionResponse> {
    private String paymentMethodType;
    private String vaultingRequestId;
    private String vaultingNotificationUrl;
    private String redirectUrl;

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getVaultingRequestId() {
        return vaultingRequestId;
    }

    public void setVaultingRequestId(String vaultingRequestId) {
        this.vaultingRequestId = vaultingRequestId;
    }

    public String getVaultingNotificationUrl() {
        return vaultingNotificationUrl;
    }

    public void setVaultingNotificationUrl(String vaultingNotificationUrl) {
        this.vaultingNotificationUrl = vaultingNotificationUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public Class<AlipayVaultingSessionResponse> getResponseClass() {
        return AlipayVaultingSessionResponse.class;
    }
}