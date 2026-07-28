package com.alipay.global.api.request;

import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.response.AlipayResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public abstract class AlipayRequest<T extends AlipayResponse> {
  /** client id */
  @JsonIgnore
  private String clientId;

  @JsonIgnore
  private String path;

  @JsonIgnore
  private Integer keyVersion;

  @JsonIgnore
  private Class<T> responseClass;

  @JsonIgnore
  private String httpMethod = HttpMethod.POST.name();

  /**
   * 是否使用沙箱url
   *
   * @return true/false
   */
  public boolean usingSandboxUrl() {
    return true;
  }

  /**
   * 得到当前API的响应结果类型
   *
   * @return 响应类型
   */
  public abstract Class<T> getResponseClass();
}
