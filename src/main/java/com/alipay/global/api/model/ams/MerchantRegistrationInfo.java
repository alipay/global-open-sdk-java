package com.alipay.global.api.model.ams;

import com.alipay.global.api.model.aps.Logo;

import java.util.List;


public class MerchantRegistrationInfo {
    private String             referenceMerchantId;
    private String             merchantDisplayName;
    private String             merchantMCC;
    private Logo               logo;
    private List<WebSite>      websites;
    private Address            merchantAddress;
    private RegistrationDetail registrationDetail;


    public String getReferenceMerchantId() {
        return referenceMerchantId;
    }


    public void setReferenceMerchantId(String referenceMerchantId) {
        this.referenceMerchantId = referenceMerchantId;
    }


    public String getMerchantDisplayName() {
        return merchantDisplayName;
    }


    public void setMerchantDisplayName(String merchantDisplayName) {
        this.merchantDisplayName = merchantDisplayName;
    }


    public String getMerchantMCC() {
        return merchantMCC;
    }


    public void setMerchantMCC(String merchantMCC) {
        this.merchantMCC = merchantMCC;
    }


    public Logo getLogo() {
        return logo;
    }


    public void setLogo(Logo logo) {
        this.logo = logo;
    }


    public List<WebSite> getWebsites() {
        return websites;
    }


    public void setWebsites(List<WebSite> websites) {
        this.websites = websites;
    }


    public Address getMerchantAddress() {
        return merchantAddress;
    }


    public void setMerchantAddress(Address merchantAddress) {
        this.merchantAddress = merchantAddress;
    }


    public RegistrationDetail getRegistrationDetail() {
        return registrationDetail;
    }


    public void setRegistrationDetail(RegistrationDetail registrationDetail) {
        this.registrationDetail = registrationDetail;
    }
}
