package com.alipay.global.api.request.ams.marketplace;

import com.alipay.global.api.model.ams.SettlementBankAccount;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketplace.AlipaySettlementInfoUpdateResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySettlementInfoUpdateRequest extends
        AlipayRequest<AlipaySettlementInfoUpdateResponse> {

    private String updateRequestId;
    private String referenceMerchantId;
    private String settlementCurrency;
    private SettlementBankAccount settlementBankAccount;

    public AlipaySettlementInfoUpdateRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_SETTLEMENTINFO_UPDATE_PATH);
    }

    @Override
    public Class<AlipaySettlementInfoUpdateResponse> getResponseClass() {
        return AlipaySettlementInfoUpdateResponse.class;
    }
}
