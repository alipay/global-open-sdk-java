package com.alipay.global.api.response.ams.vaults;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayUpdateTokenResponse extends AlipayResponse {
  private String token;
}
