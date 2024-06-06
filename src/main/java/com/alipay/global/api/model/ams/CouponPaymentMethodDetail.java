package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponPaymentMethodDetail {

    private String couponId;
    private Amount availableAmount;
    private String couponName;
    private String couponDescription;
    private String couponExpireTime;
    private String paymentMethodDetailMetadata;

}
