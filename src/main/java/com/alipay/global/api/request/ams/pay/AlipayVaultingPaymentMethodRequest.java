package com.alipay.global.api.request.ams.pay;

import com.alipay.global.api.model.ams.CustomizedInfo;
import com.alipay.global.api.model.ams.Env;
import com.alipay.global.api.model.ams.PaymentMethodDetail;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayVaultingPaymentMethodResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayVaultingPaymentMethodRequest extends
        AlipayRequest<AlipayVaultingPaymentMethodResponse> {

    private String vaultingRequestId;

    private String vaultingNotificationUrl;

    private String redirectUrl;

    private String merchantRegion;

    private PaymentMethodDetail paymentMethodDetail;

    private Env env;

    private String merchantAccountId;

    private String vaultingCurrency;

    private CustomizedInfo customizedInfo;


    public AlipayVaultingPaymentMethodRequest() {
        this.setPath(AntomPathConstants.VAULT_PAYMENT_METHOD_PATH);
    }

    @Override
    public Class<AlipayVaultingPaymentMethodResponse> getResponseClass() {
        return AlipayVaultingPaymentMethodResponse.class;
    }

}
