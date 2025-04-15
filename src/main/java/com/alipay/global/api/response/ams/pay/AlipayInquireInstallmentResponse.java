package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.InstallmentBank;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayInquireInstallmentResponse extends AlipayResponse {
    private InstallmentBank installmentBanks;
}
