package com.alipay.global.api.response.ams.merchant;

import com.alipay.global.api.model.ams.MerchantRegistrationInfo;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayMerchantRegistrationInfoQueryResponse extends AlipayResponse {

    private MerchantRegistrationInfo merchantInfo;
    private List<ProductCodeType> productCodes;

}
