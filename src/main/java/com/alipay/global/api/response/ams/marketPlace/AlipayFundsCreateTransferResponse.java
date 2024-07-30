package com.alipay.global.api.response.ams.marketPlace;


import com.alipay.global.api.model.ams.TransferFromDetail;
import com.alipay.global.api.model.ams.TransferToDetail;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayFundsCreateTransferResponse extends AlipayResponse {
    private String              transferId;
    private String              transferRequestId;
    private TransferFromDetail  transferFromDetail;
    private TransferToDetail    transferToDetail;


}