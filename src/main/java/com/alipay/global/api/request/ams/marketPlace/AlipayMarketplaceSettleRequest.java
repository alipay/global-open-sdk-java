package com.alipay.global.api.request.ams.marketPlace;


import com.alipay.global.api.model.ams.SettlementDetail;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketPlace.AlipayMarketplaceSettleResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayMarketplaceSettleRequest extends AlipayRequest<AlipayMarketplaceSettleResponse> {

    private String                  settlementRequestId;
    private String                  paymentId;
    private List<SettlementDetail>  settlementDetails;



    public AlipayMarketplaceSettleRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_SETTLE_PATH);
    }


    @Override
    public Class<AlipayMarketplaceSettleResponse> getResponseClass() {
        return AlipayMarketplaceSettleResponse.class;
    }
}
