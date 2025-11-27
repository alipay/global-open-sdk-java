package com.alipay.global.api.model.aps;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

  private String quoteId;
  private String quoteCurrencyPair;
  private BigDecimal quotePrice;
  private String quoteStartTime;
  private String quoteExpiryTime;
  private String baseCurrency;
  private String quoteUnit;
}
