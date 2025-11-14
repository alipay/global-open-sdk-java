package com.alipay.global.api.request.ams.auth;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.auth.AlipayAuthQueryTokenResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayAuthQueryTokenRequest extends AlipayRequest<AlipayAuthQueryTokenResponse> {

  private String accessToken;

  public AlipayAuthQueryTokenRequest() {
    this.setPath("/ams/api/v1/authorizations/query");
  }

  @Override
  public Class<AlipayAuthQueryTokenResponse> getResponseClass() {
    return AlipayAuthQueryTokenResponse.class;
  }
}
