/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

/** The enumeration value of the API call phase 调用API阶段的枚举值 */
public enum AuthorizationPhase {
  /** Indicates that you initiated this call before the card payment was authorized. */
  PRE_AUTHORIZATION,
  /** Indicates that you initiated this call after the card payment was authorized */
  POST_AUTHORIZATION;
}
