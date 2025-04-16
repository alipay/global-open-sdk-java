package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayPaymentSessionResponse extends AlipayResponse {

    private String paymentSessionData;

    private String paymentSessionExpiryTime;

    private String paymentSessionId;

    private String normalUrl;

    private String url;

}
