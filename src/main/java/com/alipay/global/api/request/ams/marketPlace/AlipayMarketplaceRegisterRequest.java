package com.alipay.global.api.request.ams.marketPlace;


import com.alipay.global.api.model.ams.MerchantInfo;
import com.alipay.global.api.model.ams.PaymentMethod;
import com.alipay.global.api.model.ams.SettlementInfo;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketPlace.AlipayMarketplaceRegisterResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayMarketplaceRegisterRequest extends AlipayRequest<AlipayMarketplaceRegisterResponse> {


    private String                  registrationRequestId;
    private List<SettlementInfo>    settlementInfos;
    private MerchantInfo            merchantInfo;
    private List<PaymentMethod>     paymentMethods;


    public AlipayMarketplaceRegisterRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_REGISTER_PATH);
    }


    @Override
    public Class<AlipayMarketplaceRegisterResponse> getResponseClass() {
        return AlipayMarketplaceRegisterResponse.class;
    }

}
