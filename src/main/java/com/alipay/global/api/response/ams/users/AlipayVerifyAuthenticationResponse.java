package com.alipay.global.api.response.ams.users;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayVerifyAuthenticationResponse extends AlipayResponse {

  private boolean isPassed;
}
