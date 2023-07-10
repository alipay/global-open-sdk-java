package com.alipay.global.api.model.ams;

/**
 * @Author yanhong
 * @version $Id: SupportBank.java, v 0.1 2023年07月10日 8:12 PM yanhong Exp $
 **/
public class SupportBank {

    private String bankIdentifierCode;

    private String bankShortName;

    private Logo bankLogo;

    public String getBankIdentifierCode() {
        return bankIdentifierCode;
    }

    public void setBankIdentifierCode(String bankIdentifierCode) {
        this.bankIdentifierCode = bankIdentifierCode;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public Logo getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(Logo bankLogo) {
        this.bankLogo = bankLogo;
    }
}
