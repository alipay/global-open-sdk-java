package com.alipay.global.api.model.aps;

import com.alipay.global.api.model.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Result transactionResult;
    private String transactionId;
    private TransactionType transactionType;
    private TransactionStatusType transactionStatus;
    private Amount transactionAmount;
    private String transactionRequestId;
    private String transactionTime;

}
