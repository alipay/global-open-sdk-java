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

    /**
     * The type of payment method to be vaulted. Valid values are:
     *
     * CARD: the card used to be vaulted
     * WALLET: the wallet used to be vaulted
     */
    private String paymentMethodType;
    private String extendInfo;

    private Wallet wallet;

    private String interactionType;

}
