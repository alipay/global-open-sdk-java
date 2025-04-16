package com.alipay.global.api.request.ams.marketplace;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketplace.AlipayInquireBalanceResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInquireBalanceRequest extends AlipayRequest<AlipayInquireBalanceResponse> {

    private String referenceMerchantId;

    public AlipayInquireBalanceRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_INQUIREBALANCE_PATH);
    }

    @Override
    public Class<AlipayInquireBalanceResponse> getResponseClass() {
        return AlipayInquireBalanceResponse.class;
    }

}
