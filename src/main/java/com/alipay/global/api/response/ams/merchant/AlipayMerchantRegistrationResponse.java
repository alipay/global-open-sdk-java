package com.alipay.global.api.response.ams.merchant;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayMerchantRegistrationResponse extends AlipayResponse {

    private String passThroughInfo;

}
