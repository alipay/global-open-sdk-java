package com.alipay.global.api.response.ams.customs;


import com.alipay.global.api.model.ams.ClearingChannel;
import com.alipay.global.api.model.ams.IdentityCheckResult;
import com.alipay.global.api.response.AlipayResponse;

public class AlipayCustomsDeclareResponse extends AlipayResponse {

    private String                  customsPaymentId;
    private String                  customsOrderId;
    private IdentityCheckResult     identityCheckResult;
    private ClearingChannel         clearingChannel;
    private String                  clearingTransactionId;
    private String                  customsProviderRegistrationId;

    public String getCustomsPaymentId() {
        return customsPaymentId;
    }

    public void setCustomsPaymentId(String customsPaymentId) {
        this.customsPaymentId = customsPaymentId;
    }

    public String getCustomsOrderId() {
        return customsOrderId;
    }

    public void setCustomsOrderId(String customsOrderId) {
        this.customsOrderId = customsOrderId;
    }

    public IdentityCheckResult getIdentityCheckResult() {
        return identityCheckResult;
    }

    public void setIdentityCheckResult(IdentityCheckResult identityCheckResult) {
        this.identityCheckResult = identityCheckResult;
    }

    public ClearingChannel getClearingChannel() {
        return clearingChannel;
    }

    public void setClearingChannel(ClearingChannel clearingChannel) {
        this.clearingChannel = clearingChannel;
    }

    public String getClearingTransactionId() {
        return clearingTransactionId;
    }

    public void setClearingTransactionId(String clearingTransactionId) {
        this.clearingTransactionId = clearingTransactionId;
    }

    public String getCustomsProviderRegistrationId() {
        return customsProviderRegistrationId;
    }

    public void setCustomsProviderRegistrationId(String customsProviderRegistrationId) {
        this.customsProviderRegistrationId = customsProviderRegistrationId;
    }
}
