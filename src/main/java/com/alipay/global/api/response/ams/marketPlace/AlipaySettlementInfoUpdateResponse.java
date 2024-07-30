package com.alipay.global.api.response.ams.marketPlace;


import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipaySettlementInfoUpdateResponse extends AlipayResponse {

    /**
     * The update status of the settlement information. The value of this parameter is fixed to PROCESSING.
     * Get the settlement information update result from the notifyUpdate and inquireUpdate interfaces.
     * This parameter is returned when the value of result.resultStatus is S.
     */
    private String updateStatus;
}
