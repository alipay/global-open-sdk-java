package com.alipay.global.api.request.ams.users;

import com.alipay.global.api.model.ams.AuthenticationChannelType;
import com.alipay.global.api.model.ams.AuthenticationType;
import com.alipay.global.api.model.ams.UserIdentityType;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.users.AlipayInitAuthenticationResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInitAuthenticationRequest
    extends AlipayRequest<AlipayInitAuthenticationResponse> {

  private AuthenticationType authenticationType;
  private String authenticationRequestId;
  private AuthenticationChannelType authenticationChannelType;
  private UserIdentityType userIdentityType;
  private String userIdentityValue;

  public AlipayInitAuthenticationRequest() {
    this.setPath("/ams/api/v1/users/initAuthentication");
  }

  @Override
  public Class<AlipayInitAuthenticationResponse> getResponseClass() {
    return AlipayInitAuthenticationResponse.class;
  }
}
