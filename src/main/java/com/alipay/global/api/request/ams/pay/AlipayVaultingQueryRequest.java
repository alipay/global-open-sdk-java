package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayVaultingQueryResponse;

public class AlipayVaultingQueryRequest extends AlipayRequest<AlipayVaultingQueryResponse> {

    private String vaultingRequestId;

    public String getVaultingRequestId() {
        return vaultingRequestId;
    }

    public AlipayVaultingQueryRequest setVaultingRequestId(String vaultingRequestId) {
        this.vaultingRequestId = vaultingRequestId;
        return this;
    }

    @Override
    public Class<AlipayVaultingQueryResponse> getResponseClass() {
        return AlipayVaultingQueryResponse.class;
    }
}
