package com.alipay.global.api.request.ams.merchant;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.merchant.AlipayMerchantRegistrationStatusQueryResponse;


public class AlipayMerchantRegistrationStatusQueryRequest extends AlipayRequest<AlipayMerchantRegistrationStatusQueryResponse> {

    private String registrationRequestId;
    private String referenceMerchantId;

    public String getRegistrationRequestId() {
        return registrationRequestId;
    }

    public void setRegistrationRequestId(String registrationRequestId) {
        this.registrationRequestId = registrationRequestId;
    }

    public String getReferenceMerchantId() {
        return referenceMerchantId;
    }

    public void setReferenceMerchantId(String referenceMerchantId) {
        this.referenceMerchantId = referenceMerchantId;
    }

    @Override
    public Class<AlipayMerchantRegistrationStatusQueryResponse> getResponseClass() {
        return AlipayMerchantRegistrationStatusQueryResponse.class;
    }
}
