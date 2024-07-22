package com.alipay.global.api.response.ams.marketPlace;


import com.alipay.global.api.model.ams.AccountBalance;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayAccountsInquireBalanceResponse extends AlipayResponse {

    private List<AccountBalance> accountBalances;
}
