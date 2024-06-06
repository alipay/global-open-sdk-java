package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDetail {

    private PaymentMethodDetailType paymentMethodDetailType;
    private CardPaymentMethodDetail card;
    private ExternalPaymentMethodDetail externalAccount;
    private DiscountPaymentMethodDetail discount;
    private CouponPaymentMethodDetail coupon;

    private String paymentMethodType;
    private String extendInfo;

}
