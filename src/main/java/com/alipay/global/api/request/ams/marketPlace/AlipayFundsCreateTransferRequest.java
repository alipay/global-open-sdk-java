package com.alipay.global.api.request.ams.marketPlace;

import com.alipay.global.api.model.ams.TransferFromDetail;
import com.alipay.global.api.model.ams.TransferToDetail;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketPlace.AlipayFundsCreateTransferResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayFundsCreateTransferRequest  extends AlipayRequest<AlipayFundsCreateTransferResponse> {


    private String              transferRequestId;
    private TransferFromDetail  transferFromDetail;
    private TransferToDetail    transferToDetail;

    public AlipayFundsCreateTransferRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_CREATETRANSFER_PATH);
    }


    @Override
    public Class<AlipayFundsCreateTransferResponse> getResponseClass() {
        return AlipayFundsCreateTransferResponse.class;
    }
}
