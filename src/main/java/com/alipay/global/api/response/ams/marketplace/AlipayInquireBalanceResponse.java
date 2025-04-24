package com.alipay.global.api.response.ams.marketplace;

import com.alipay.global.api.model.ams.AccountBalance;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInquireBalanceResponse extends AlipayResponse {

    private List<AccountBalance> accountBalances;

}
