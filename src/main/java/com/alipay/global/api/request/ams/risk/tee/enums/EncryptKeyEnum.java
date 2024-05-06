package com.alipay.global.api.request.ams.risk.tee.enums;

/**
 * Enumerates merchant selectable encryption keys.
 * 枚举商户可选择的加密字段
 */
public enum EncryptKeyEnum {
    MERCHANT_EMAIL("MERCHANT_EMAIL", "orders.merchant.merchantEmail"),
    BUYER_REGISTRATION_TIME("BUYER_REGISTRATION_TIME", "buyer.buyerRegistrationTime"),
    CARD_HOLDER_NAME("CARD_HOLDER_NAME", "paymentDetails.paymentMethod.paymentMethodMetaData.cardholderName"),
    SHIPPING_ADDR1("SHIPPING_ADDR1", "orders.shipping.shippingAddress.address1"),
    SHIPPING_ADDR2("SHIPPING_ADDR2", "orders.shipping.shippingAddress.address2"),
    SHIPPING_NAME("SHIPPING_NAME", "orders.shipping.shippingName"),
    SHIPPING_EMAIL("SHIPPING_EMAIL", "orders.shipping.shipToEmail"),
    SHIPPING_PHONE_NO("SHIPPING_PHONE_NO", "orders.shipping.shippingPhoneNo")

    ;
    private final String code;

    private final String description;

    EncryptKeyEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static EncryptKeyEnum getEnumByCode(String code) {

        for (EncryptKeyEnum codeEnum : values()) {
            if (codeEnum.getCode().equals(code)) {
                return codeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() { return description; }
}
