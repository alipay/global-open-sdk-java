package com.alipay.global.api.response.ams.auth;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAuthQueryTokenResponse extends AlipayResponse {

  private String accessToken;
  private String accessTokenExpiryTime;
  private String refreshToken;
  private String refreshTokenExpiryTime;
  private String tokenStatusType;
}
