package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalPaymentMethodDetail {

    private String assetToken;
    private String accountDisplayName;
    private String disableReason;
    private String paymentMethodDetailMetadata;

}
