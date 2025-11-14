package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.Quote;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInquireExchangeRateResponse extends AlipayPayResponse {
  private List<Quote> quotes;
}
