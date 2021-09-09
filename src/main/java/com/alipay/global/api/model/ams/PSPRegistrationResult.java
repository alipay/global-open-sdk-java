package com.alipay.global.api.model.ams;


public class PSPRegistrationResult {

    private RegistrationResult registrationResult;
    private String             pspName;


    public RegistrationResult getRegistrationResult() {
        return registrationResult;
    }

    public void setRegistrationResult(RegistrationResult registrationResult) {
        this.registrationResult = registrationResult;
    }

    public String getPspName() {
        return pspName;
    }

    public void setPspName(String pspName) {
        this.pspName = pspName;
    }
}
