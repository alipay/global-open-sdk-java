package com.alipay.global.api.request.ams.merchant;

import java.util.List;

import com.alipay.global.api.model.ams.MerchantRegistrationInfo;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.merchant.AlipayMerchantRegistrationResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayMerchantRegistrationRequest extends
                                               AlipayRequest<AlipayMerchantRegistrationResponse> {

    private List<ProductCodeType>    productCodes;
    private String                   registrationRequestId;
    private String                   registrationNotifyURL;
    private MerchantRegistrationInfo merchantInfo;
    private String                   passThroughInfo;

    public AlipayMerchantRegistrationRequest() {
        this.setPath("/ams/api/v1/merchants/registration");
    }

    @Override
    public Class<AlipayMerchantRegistrationResponse> getResponseClass() {
        return AlipayMerchantRegistrationResponse.class;
    }
}
