package com.alipay.global.api.request.ams.vaults;

import com.alipay.global.api.model.ams.UserName;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.vaults.AlipayUpdateTokenResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayUpdateTokenRequest extends AlipayRequest<AlipayUpdateTokenResponse> {
  private String merchantAccountId;
  private String token;
  private UserName accountHolderName;
  private String email;
  private String tokenExpiryTime;

  public AlipayUpdateTokenRequest() {
    this.setPath(AntomPathConstants.UPDATE_TOKEN_VAULTING_PATH);
  }

  @Override
  public Class<AlipayUpdateTokenResponse> getResponseClass() {
    return AlipayUpdateTokenResponse.class;
  }
}
