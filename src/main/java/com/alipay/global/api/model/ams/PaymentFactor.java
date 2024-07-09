package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFactor {

    private Boolean isPaymentEvaluation;

    private InStorePaymentScenario inStorePaymentScenario;

    private PresentmentMode presentmentMode;

    private String captureMode;

    private Boolean isAuthorization;

}
