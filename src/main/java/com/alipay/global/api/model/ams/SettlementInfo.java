package com.alipay.global.api.model.ams;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementInfo {

    private String                         settlementCurrency;
    private List<SettlementBankAccount>    settlementBankAccount;

}
