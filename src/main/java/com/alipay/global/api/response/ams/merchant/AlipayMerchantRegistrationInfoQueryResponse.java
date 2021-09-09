package com.alipay.global.api.response.ams.merchant;

import com.alipay.global.api.model.ams.MerchantRegistrationInfo;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.response.AlipayResponse;

import java.util.List;


public class AlipayMerchantRegistrationInfoQueryResponse extends AlipayResponse {

    private MerchantRegistrationInfo merchantInfo;
    private List<ProductCodeType>    productCodes;

    public MerchantRegistrationInfo getMerchantInfo() {
        return merchantInfo;
    }

    public void setMerchantInfo(MerchantRegistrationInfo merchantInfo) {
        this.merchantInfo = merchantInfo;
    }

    public List<ProductCodeType> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<ProductCodeType> productCodes) {
        this.productCodes = productCodes;
    }
}
