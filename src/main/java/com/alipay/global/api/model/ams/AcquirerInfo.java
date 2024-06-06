/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcquirerInfo {

    private String acquirerName;

    private String referenceRequestId;

    private String acquirerTransactionId;

    private String acquirerMerchantId;

    private String acquirerResultCode;

    private String acquirerResultMessage;

}