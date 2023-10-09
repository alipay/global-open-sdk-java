package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ams.VaultingPaymentMethodDetail;

public class AlipayVaultingNotify {
    private Result                      result;
    private String                      vaultingRequestId;
    private VaultingPaymentMethodDetail paymentMethodDetail;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getVaultingRequestId() {
        return vaultingRequestId;
    }

    public void setVaultingRequestId(String vaultingRequestId) {
        this.vaultingRequestId = vaultingRequestId;
    }

    public VaultingPaymentMethodDetail getPaymentMethodDetail() {
        return paymentMethodDetail;
    }

    public void setPaymentMethodDetail(VaultingPaymentMethodDetail paymentMethodDetail) {
        this.paymentMethodDetail = paymentMethodDetail;
    }
}