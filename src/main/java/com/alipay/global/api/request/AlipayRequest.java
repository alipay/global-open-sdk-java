package com.alipay.global.api.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.alipay.global.api.net.HttpMethod;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;

@Data
public abstract class AlipayRequest<T extends AlipayResponse> {
  /** client id */
  @JSONField(serialize = false)
  private String clientId;

  @JSONField(serialize = false)
  private String path;

  @JSONField(serialize = false)
  private Integer keyVersion;

  @JSONField(serialize = false)
  private Class<T> responseClass;

  @JSONField(serialize = false)
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
