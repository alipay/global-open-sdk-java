package com.alipay.global.api.model.ams;

import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(name = "isAuthorization")
    private Boolean isAuthorization;

    public Boolean isAuthorization() {
        return isAuthorization;
    }

}
