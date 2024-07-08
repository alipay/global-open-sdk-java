package com.alipay.global.api.response.ams.merchant;

import java.util.List;

import com.alipay.global.api.model.ams.PSPRegistrationResult;
import com.alipay.global.api.model.ams.RegistrationResult;
import com.alipay.global.api.response.AlipayResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayMerchantRegistrationStatusQueryResponse extends AlipayResponse {

    private RegistrationResult          registrationResult;
    private List<PSPRegistrationResult> pspRegistrationResultList;

}
