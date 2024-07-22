package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalance {
    private String accountNo;
    private String currency;
    private Amount availableBalance;
    private Amount frozenBalance;
    private Amount totalBalance;
}
