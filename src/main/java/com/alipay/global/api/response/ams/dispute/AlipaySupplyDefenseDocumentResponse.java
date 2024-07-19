package com.alipay.global.api.response.ams.dispute;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipaySupplyDefenseDocumentResponse  extends AlipayResponse {

    private String      disputeId;
    private String      disputeResolutionTime;

}
