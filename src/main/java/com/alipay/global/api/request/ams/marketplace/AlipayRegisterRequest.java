package com.alipay.global.api.request.ams.marketplace;

import com.alipay.global.api.model.ams.MerchantInfo;
import com.alipay.global.api.model.ams.PaymentMethod;
import com.alipay.global.api.model.ams.SettlementInfo;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketplace.AlipayRegisterResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayRegisterRequest extends AlipayRequest<AlipayRegisterResponse> {

    private String registrationRequestId;
    private List<SettlementInfo> settlementInfos;
    private MerchantInfo merchantInfo;
    private List<PaymentMethod> paymentMethods;

    public AlipayRegisterRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_REGISTER_PATH);
    }

    @Override
    public Class<AlipayRegisterResponse> getResponseClass() {
        return AlipayRegisterResponse.class;
    }

}
