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
public class PaymentMethod {
  /** The type of payment method included in the payment method options 支付方式选项中包含的支付方式类型 */
  private String paymentMethodType;
  /** A unique ID used to identify the payment method 用于识别支付方式的唯一ID */
  private String paymentMethodId;
  /** More information about the card. 有关该卡的详细信息。 */
  private PaymentMethodMetaData paymentMethodMetaData;
}
