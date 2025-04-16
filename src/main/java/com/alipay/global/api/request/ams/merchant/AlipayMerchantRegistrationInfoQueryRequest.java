package com.alipay.global.api.request.ams.merchant;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.merchant.AlipayMerchantRegistrationInfoQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayMerchantRegistrationInfoQueryRequest extends
        AlipayRequest<AlipayMerchantRegistrationInfoQueryResponse> {

    private String referenceMerchantId;

    public AlipayMerchantRegistrationInfoQueryRequest() {
        this.setPath("/ams/api/v1/merchants/inquiryRegistrationInfo");
    }

    @Override
    public Class<AlipayMerchantRegistrationInfoQueryResponse> getResponseClass() {
        return AlipayMerchantRegistrationInfoQueryResponse.class;
    }
}
