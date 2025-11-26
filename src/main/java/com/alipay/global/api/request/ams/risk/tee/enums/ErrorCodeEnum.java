/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk.tee.enums;

/** ErrorCode enumeration class that may occur during encryption 加密过程中可能出现的 ErrorCode 枚举类 */
public enum ErrorCodeEnum {
  PARAM_ILLEGAL("PARAM_ILLEGAL", "param illegal"),
  ENCRYPT_ERROR("ENCRYPT_ERROR", "encrypt error"),
  UNKNOWN_ENCRYPT_KEY("UNKNOWN_ENCRYPT_KEY", "unknown encrypt key"),
  MISMATCH_ENCRYPT_UTIL("MISMATCH_ENCRYPT_UTIL", "mismatch encrypt util"),
  ;

  private final String code;

  private final String description;

  ErrorCodeEnum(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public static ErrorCodeEnum getEnumByCode(String code) {

    for (ErrorCodeEnum codeEnum : values()) {
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
