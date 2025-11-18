package com.alipay.global.api.request.ams.vaults;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.vaults.AlipayVaultingInquireTokenResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayVaultingInquireTokenRequest
    extends AlipayRequest<AlipayVaultingInquireTokenResponse> {
  private String merchantAccountId;
  private String token;

  public AlipayVaultingInquireTokenRequest() {
    this.setPath(AntomPathConstants.INQUIRE_TOKEN_VAULTING_PATH);
  }

  @Override
  public Class<AlipayVaultingInquireTokenResponse> getResponseClass() {
    return AlipayVaultingInquireTokenResponse.class;
  }
}
