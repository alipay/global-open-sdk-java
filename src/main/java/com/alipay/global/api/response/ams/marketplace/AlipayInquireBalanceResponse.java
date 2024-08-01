package com.alipay.global.api.response.ams.marketplace;

import java.util.List;

import com.alipay.global.api.model.ams.AccountBalance;
import com.alipay.global.api.response.AlipayResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayInquireBalanceResponse extends AlipayResponse {

    private List<AccountBalance> accountBalances;

}
