package com.alipay.global.api.model.ams;

/**
 * @Author yanhong
 * @version $Id: SupportCardBrand.java, v 0.1 2023年07月10日 7:57 PM yanhong Exp $
 **/
public class SupportCardBrand {

    private String cardBrand;

    private Logo logo;

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }
}
