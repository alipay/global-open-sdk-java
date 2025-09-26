package com.alipay.global.api.request.ams.users;

import com.alipay.global.api.model.ams.AuthenticationType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.users.AlipayVerifyAuthenticationResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayVerifyAuthenticationRequest
    extends AlipayRequest<AlipayVerifyAuthenticationResponse> {

  private AuthenticationType authenticationType;
  private String authenticationRequestId;
  private String authenticationValue;

  public AlipayVerifyAuthenticationRequest() {
    this.setPath("/ams/api/v1/users/verifyAuthentication");
  }

  @Override
  public Class<AlipayVerifyAuthenticationResponse> getResponseClass() {
    return AlipayVerifyAuthenticationResponse.class;
  }
}
