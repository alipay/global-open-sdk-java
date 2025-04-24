package com.alipay.global.api.request.ams.marketplace;

import com.alipay.global.api.model.ams.TransferFromDetail;
import com.alipay.global.api.model.ams.TransferToDetail;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketplace.AlipayCreateTransferResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCreateTransferRequest extends AlipayRequest<AlipayCreateTransferResponse> {

    private String transferRequestId;
    private TransferFromDetail transferFromDetail;
    private TransferToDetail transferToDetail;

    public AlipayCreateTransferRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_CREATETRANSFER_PATH);
    }

    @Override
    public Class<AlipayCreateTransferResponse> getResponseClass() {
        return AlipayCreateTransferResponse.class;
    }
}
