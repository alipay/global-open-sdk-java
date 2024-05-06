package com.alipay.global.api.request.ams.risk.tee.enums;

public enum EncryptKeyEnum {
    SHIPPINGADDR_ADDR1("SHIPPINGADDR_ADDR1", "orders.shipping.shippingAddress.address1"),

    SHIPPINGADDR_ADDR2("SHIPPINGADDR_ADDR2", "orders.shipping.shippingAddress.address2"),

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
