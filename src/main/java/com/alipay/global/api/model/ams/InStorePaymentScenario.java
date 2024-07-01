package com.alipay.global.api.model.ams;

public enum InStorePaymentScenario {
    /**
     * User can use paymentcode to pay
     */
    PaymentCode,
    /**
     * User can use orderCode to finish pay
     */
    OrderCode,

    /**
     * The third party code
     */
    EntryCode;
}
