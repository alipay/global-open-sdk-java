package com.alipay.global.api.response;

import com.alipay.global.api.model.Result;
import lombok.Data;

@Data
public class AlipayResponse {

  private Result result;

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }
}
