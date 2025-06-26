package com.alipay.global.api.model.aps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {

  private UserName shippingName;
  private Address shippingAddress;
  private String shippingCarrier;
  private String shippingPhoneNo;
}
