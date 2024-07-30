package com.alipay.global.api.response.ams.marketPlace;


import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayMarketplaceRegisterResponse extends AlipayResponse {

    /**
     * The registration status of the merchant. The value of this parameter is fixed to PROCESSING.
     * Get the sub-merchant's registration result from the notifyRegistration interface.
     * This parameter is returned when the value of result.resultStatus is S.
     */
    private String registrationStatus;
}
