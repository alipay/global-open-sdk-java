package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.PaymentMethodDetail;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayVaultingQueryResponse extends AlipayResponse {

    private String vaultingRequestId;

    private String normalUrl;

    private String schemeUrl;

    private String applinkUrl;

    private String vaultingStatus;

    private PaymentMethodDetail paymentMethodDetail;

    public String getVaultingRequestId() {
        return vaultingRequestId;
    }

    public AlipayVaultingQueryResponse setVaultingRequestId(String vaultingRequestId) {
        this.vaultingRequestId = vaultingRequestId;
        return this;
    }

    public String getNormalUrl() {
        return normalUrl;
    }

    public AlipayVaultingQueryResponse setNormalUrl(String normalUrl) {
        this.normalUrl = normalUrl;
        return this;
    }

    public String getSchemeUrl() {
        return schemeUrl;
    }

    public AlipayVaultingQueryResponse setSchemeUrl(String schemeUrl) {
        this.schemeUrl = schemeUrl;
        return this;
    }

    public String getApplinkUrl() {
        return applinkUrl;
    }

    public void setApplinkUrl(String applinkUrl) {
        this.applinkUrl = applinkUrl;
    }

    public String getVaultingStatus() {
        return vaultingStatus;
    }

    public void setVaultingStatus(String vaultingStatus) {
        this.vaultingStatus = vaultingStatus;
    }

    public PaymentMethodDetail getPaymentMethodDetail() {
        return paymentMethodDetail;
    }

    public void setPaymentMethodDetail(PaymentMethodDetail paymentMethodDetail) {
        this.paymentMethodDetail = paymentMethodDetail;
    }
}
