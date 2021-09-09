package com.alipay.global.api.response.ams.merchant;

import com.alipay.global.api.model.ams.PSPRegistrationResult;
import com.alipay.global.api.model.ams.RegistrationResult;
import com.alipay.global.api.response.AlipayResponse;

import java.util.List;


public class AlipayMerchantRegistrationStatusQueryResponse extends AlipayResponse {

    private RegistrationResult          registrationResult;
    private List<PSPRegistrationResult> pspRegistrationResultList;


    public RegistrationResult getRegistrationResult() {
        return registrationResult;
    }

    public void setRegistrationResult(RegistrationResult registrationResult) {
        this.registrationResult = registrationResult;
    }

    public List<PSPRegistrationResult> getPspRegistrationResultList() {
        return pspRegistrationResultList;
    }

    public void setPspRegistrationResultList(List<PSPRegistrationResult> pspRegistrationResultList) {
        this.pspRegistrationResultList = pspRegistrationResultList;
    }
}
