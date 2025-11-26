/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk.tee.enums;

/** Merchant selectable encryption keys enumeration class 商户可选择的加密字段枚举类 */
public enum EncryptKeyEnum {
  BUYER_EMAIL("buyerEmail", "buyer.buyerEmail"),
  BUYER_PHONE_NO("buyerPhoneNo", "buyer.buyerPhoneNo"),
  BUYER_REGISTRATION_TIME("buyerRegistrationTime", "buyer.buyerRegistrationTime"),
  CARDHOLDER_NAME(
      "cardHolderName", "paymentDetails.paymentMethod.paymentMethodMetaData.cardHolderName"),
  SHIPPING_ADDRESS1("address1", "orders.shipping.shippingAddress.address1"),
  SHIPPING_ADDRESS2("address2", "orders.shipping.shippingAddress.address2"),
  SHIPPING_NAME("shippingName", "orders.shipping.shippingName"),
  SHIP_TO_EMAIL("shipToEmail", "orders.shipping.shipToEmail"),
  SHIPPING_PHONE_NO("shippingPhoneNo", "orders.shipping.shippingPhoneNo");
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

  public String getDescription() {
    return description;
  }
}
