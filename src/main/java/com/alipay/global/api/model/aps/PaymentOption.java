package com.alipay.global.api.model.aps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOption {

  private String paymentMethodType;
  private PaymentMethodCategoryType paymentMethodCategory;
  private boolean enabled;
  private boolean preferred;
  private String disableReason;
  private Logo logo;
  private PaymentOptionDetail paymentOptionDetail;
}
