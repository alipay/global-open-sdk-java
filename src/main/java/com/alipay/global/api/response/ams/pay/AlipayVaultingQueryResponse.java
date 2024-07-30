package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.PaymentMethodDetail;
import com.alipay.global.api.model.ams.VaultingStatus;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayVaultingQueryResponse extends AlipayResponse {

    private String              vaultingRequestId;

    private String              normalUrl;

    private String              schemeUrl;

    private String              applinkUrl;

    private VaultingStatus      vaultingStatus;

    private PaymentMethodDetail paymentMethodDetail;

}
