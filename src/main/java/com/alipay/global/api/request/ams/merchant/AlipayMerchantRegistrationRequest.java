package com.alipay.global.api.request.ams.merchant;

import com.alipay.global.api.model.ams.MerchantRegistrationInfo;
import com.alipay.global.api.model.ams.ProductCodeType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.merchant.AlipayMerchantRegistrationResponse;

import java.util.List;


public class AlipayMerchantRegistrationRequest extends AlipayRequest<AlipayMerchantRegistrationResponse> {



    private List<ProductCodeType>       productCodes;
    private String                      registrationRequestId;
    private String                      registrationNotifyURL;
    private MerchantRegistrationInfo    merchantInfo;
    private String                      passThroughInfo;


    public List<ProductCodeType> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<ProductCodeType> productCodes) {
        this.productCodes = productCodes;
    }

    public String getRegistrationRequestId() {
        return registrationRequestId;
    }

    public void setRegistrationRequestId(String registrationRequestId) {
        this.registrationRequestId = registrationRequestId;
    }

    public String getRegistrationNotifyURL() {
        return registrationNotifyURL;
    }

    public void setRegistrationNotifyURL(String registrationNotifyURL) {
        this.registrationNotifyURL = registrationNotifyURL;
    }

    public MerchantRegistrationInfo getMerchantInfo() {
        return merchantInfo;
    }

    public void setMerchantInfo(MerchantRegistrationInfo merchantInfo) {
        this.merchantInfo = merchantInfo;
    }

    public String getPassThroughInfo() {
        return passThroughInfo;
    }

    public void setPassThroughInfo(String passThroughInfo) {
        this.passThroughInfo = passThroughInfo;
    }

    @Override
    public Class<AlipayMerchantRegistrationResponse> getResponseClass() {
        return AlipayMerchantRegistrationResponse.class;
    }
}
