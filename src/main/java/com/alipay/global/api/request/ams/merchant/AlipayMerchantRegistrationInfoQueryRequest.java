package com.alipay.global.api.request.ams.merchant;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.merchant.AlipayMerchantRegistrationInfoQueryResponse;


public class AlipayMerchantRegistrationInfoQueryRequest extends AlipayRequest<AlipayMerchantRegistrationInfoQueryResponse> {

    private String referenceMerchantId;

    public String getReferenceMerchantId() {
        return referenceMerchantId;
    }

    public void setReferenceMerchantId(String referenceMerchantId) {
        this.referenceMerchantId = referenceMerchantId;
    }

    @Override
    public Class<AlipayMerchantRegistrationInfoQueryResponse> getResponseClass() {
        return AlipayMerchantRegistrationInfoQueryResponse.class;
    }
}
