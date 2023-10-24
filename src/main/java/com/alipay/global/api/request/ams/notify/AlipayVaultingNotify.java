package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.Result;
import com.alipay.global.api.model.ams.AcquirerInfo;
import com.alipay.global.api.model.ams.VaultingPaymentMethodDetail;

public class AlipayVaultingNotify extends AlipayNotify {
    private String                      vaultingRequestId;
    private VaultingPaymentMethodDetail paymentMethodDetail;

    private String vaultingCreateTime;

    private AcquirerInfo acquirerInfo;

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

    public String getVaultingCreateTime() {
        return vaultingCreateTime;
    }

    public void setVaultingCreateTime(String vaultingCreateTime) {
        this.vaultingCreateTime = vaultingCreateTime;
    }

    public AcquirerInfo getAcquirerInfo() {
        return acquirerInfo;
    }

    public void setAcquirerInfo(AcquirerInfo acquirerInfo) {
        this.acquirerInfo = acquirerInfo;
    }
}