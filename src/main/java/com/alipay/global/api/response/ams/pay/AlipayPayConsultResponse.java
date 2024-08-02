package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.PaymentMethodInfo;
import com.alipay.global.api.model.ams.PaymentOption;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayPayConsultResponse extends AlipayResponse {

    private List<PaymentOption> paymentOptions;
    private List<PaymentMethodInfo> paymentMethodInfos;
    private String extendInfo;

}
