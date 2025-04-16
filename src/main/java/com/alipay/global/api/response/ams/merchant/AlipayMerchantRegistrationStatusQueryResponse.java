package com.alipay.global.api.response.ams.merchant;

import com.alipay.global.api.model.ams.PSPRegistrationResult;
import com.alipay.global.api.model.ams.RegistrationResult;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayMerchantRegistrationStatusQueryResponse extends AlipayResponse {

    private RegistrationResult registrationResult;
    private List<PSPRegistrationResult> pspRegistrationResultList;

}
