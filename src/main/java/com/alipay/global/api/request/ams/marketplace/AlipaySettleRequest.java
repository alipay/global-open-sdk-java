package com.alipay.global.api.request.ams.marketplace;

import com.alipay.global.api.model.ams.SettlementDetail;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketplace.AlipaySettleResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySettleRequest extends AlipayRequest<AlipaySettleResponse> {

    private String settlementRequestId;
    private String paymentId;
    private List<SettlementDetail> settlementDetails;

    public AlipaySettleRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_SETTLE_PATH);
    }

    @Override
    public Class<AlipaySettleResponse> getResponseClass() {
        return AlipaySettleResponse.class;
    }
}
