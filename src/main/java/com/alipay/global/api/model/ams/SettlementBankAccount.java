package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementBankAccount {

    private String bankAccountNo;
    private String accountHolderName;
    private String swiftCode;
    private String bankRegion;
    private AccountHolderType accountHolderType;
    private String routingNumber;
    private String branchCode;
    private String accountHolderTIN;
    private AccountType accountType;
    private String bankName;
    private Address accountHolderAddress;
    private String iban;

}
