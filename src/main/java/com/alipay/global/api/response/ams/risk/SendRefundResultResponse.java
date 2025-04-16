/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.response.ams.risk;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The response of Ant Group's risk send refund result API.
 * 调用蚂蚁集团风控退款结果通知接口的响应结果。
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SendRefundResultResponse extends RiskResponse {
}