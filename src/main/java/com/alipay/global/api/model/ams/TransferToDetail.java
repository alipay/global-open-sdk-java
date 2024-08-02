package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferToDetail {

    private PaymentMethod transferToMethod;
    private String transferToCurrency;
    private Amount feeAmount;
    private Amount actualTransferToAmount;
    /**
     * Defines the purpose of the payout.
     * The value of this parameter is fixed to GSD, which means goods bought or sold.
     */
    private String purposeCode;
    private String transferNotifyUrl;
    private String transferRemark;
}
