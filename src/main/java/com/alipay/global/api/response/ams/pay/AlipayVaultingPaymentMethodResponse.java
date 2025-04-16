package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.PaymentMethodDetail;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayVaultingPaymentMethodResponse extends AlipayResponse {

    private String vaultingRequestId;

    private PaymentMethodDetail paymentMethodDetail;

    private String normalUrl;

    private String schemeUrl;

    private String applinkUrl;

}
