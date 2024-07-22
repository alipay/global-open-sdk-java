package com.alipay.global.api.request.ams.marketPlace;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketPlace.AlipayAccountsInquireBalanceResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayAccountsInquireBalanceRequest  extends AlipayRequest<AlipayAccountsInquireBalanceResponse> {


    private String          referenceMerchantId;



    public AlipayAccountsInquireBalanceRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_INQUIREBALANCE_PATH);
    }


    @Override
    public Class<AlipayAccountsInquireBalanceResponse> getResponseClass() {
        return AlipayAccountsInquireBalanceResponse.class;
    }


}
