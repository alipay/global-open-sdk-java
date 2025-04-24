package com.alipay.global.api.request.ams.marketplace;

import com.alipay.global.api.model.ams.TransferFromDetail;
import com.alipay.global.api.model.ams.TransferToDetail;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketplace.AlipayCreatePayoutResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCreatePayoutRequest extends AlipayRequest<AlipayCreatePayoutResponse> {

    private String transferRequestId;
    private TransferFromDetail transferFromDetail;
    private TransferToDetail transferToDetail;

    public AlipayCreatePayoutRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_CREATEPAYOUT_PATH);
    }

    @Override
    public Class<AlipayCreatePayoutResponse> getResponseClass() {
        return AlipayCreatePayoutResponse.class;
    }

}
