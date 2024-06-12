/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk.tee.enums;

/**
 * Merchant selectable encryption keys enumeration class
 * 商户可选择的加密字段枚举类
 */
public enum EncryptKeyEnum {
    BUYER_EMAIL("BUYER_EMAIL", "buyer.buyerEmail"),
    BUYER_PHONE_NO("BUYER_PHONE_NO", "buyer.buyerPhoneNo"),
    BUYER_REGISTRATION_TIME("BUYER_REGISTRATION_TIME", "buyer.buyerRegistrationTime"),
    BILL_TO_NAME("BILL_TO_NAME", "paymentDetails.paymentMethod.paymentMethodMetaData.cardholderName"),
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
