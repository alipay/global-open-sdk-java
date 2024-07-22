package com.alipay.global.api.response.ams.marketPlace;


import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipaySettlementInfoUpdateResponse extends AlipayResponse {
    private String updateStatus;
}
