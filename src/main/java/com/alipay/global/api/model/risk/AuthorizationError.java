/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationError {
    /**
     * Authorization error code.
     * 授权错误码
     */
    private String errorCode;
    /**
     * Description of the Authorization error code.
     * 授权错误码的描述
     */
    private String errorMessage;

}