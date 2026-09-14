package com.alipay.global.api.response.ams.users;

import com.alipay.global.api.response.AlipayResponse;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayVerifyAuthenticationResponse extends AlipayResponse {

  private boolean isPassed;

  @JsonProperty("isPassed")
  public boolean isPassed() {
    return isPassed;
  }

  @JsonProperty("isPassed")
  @JsonAlias("passed")
  public void setPassed(boolean passed) {
    this.isPassed = passed;
  }
}
