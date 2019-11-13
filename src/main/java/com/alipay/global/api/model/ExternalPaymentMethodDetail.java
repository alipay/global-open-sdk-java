package com.alipay.global.api.model;

public class ExternalPaymentMethodDetail {

    private String assetToken;
    private String accountDisplayName;
    private String disableReason;
    private String paymentMethodDetailMetadata;

    public String getAssetToken() {
        return assetToken;
    }

    public void setAssetToken(String assetToken) {
        this.assetToken = assetToken;
    }

    public String getAccountDisplayName() {
        return accountDisplayName;
    }

    public void setAccountDisplayName(String accountDisplayName) {
        this.accountDisplayName = accountDisplayName;
    }

    public String getDisableReason() {
        return disableReason;
    }

    public void setDisableReason(String disableReason) {
        this.disableReason = disableReason;
    }

    public String getPaymentMethodDetailMetadata() {
        return paymentMethodDetailMetadata;
    }

    public void setPaymentMethodDetailMetadata(String paymentMethodDetailMetadata) {
        this.paymentMethodDetailMetadata = paymentMethodDetailMetadata;
    }
}
