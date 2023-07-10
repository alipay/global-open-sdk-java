package com.alipay.global.api.model.ams;

import java.util.List;

/**
 * @Author yanhong
 * @version $Id: paymentOptionDetail.java, v 0.1 2023年07月10日 7:57 PM yanhong Exp $
 **/
public class PaymentOptionDetail {

    private List<SupportCardBrand> supportCardBrands;

    private List<String>           funding;

    private List<SupportBank>     supportBanks;

    public List<SupportCardBrand> getSupportCardBrands() {
        return supportCardBrands;
    }

    public void setSupportCardBrands(List<SupportCardBrand> supportCardBrands) {
        this.supportCardBrands = supportCardBrands;
    }

    public List<String> getFunding() {
        return funding;
    }

    public void setFunding(List<String> funding) {
        this.funding = funding;
    }

    public List<SupportBank> getSupportBanks() {
        return supportBanks;
    }

    public void setSupportBanks(List<SupportBank> supportBanks) {
        this.supportBanks = supportBanks;
    }
}
