package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayFetchNonceResponse extends AlipayResponse {
  private String cardToken;
}
