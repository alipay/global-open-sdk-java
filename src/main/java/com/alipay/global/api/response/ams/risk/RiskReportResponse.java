/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.response.ams.risk;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** The response of Ant Group's risk report API. 调用蚂蚁集团风控风险上报接口的响应结果。 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RiskReportResponse extends RiskResponse {}
