package com.alipay.global.api.response.ams.marketplace;

import com.alipay.global.api.response.AlipayResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipaySettleResponse extends AlipayResponse {
    private String settlementRequestId;
    private String settlementId;
}
