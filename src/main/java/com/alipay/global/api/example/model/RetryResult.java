package com.alipay.global.api.example.model;

public class RetryResult {

  private Boolean isRetry;
  private Object obj;

  private RetryResult(Boolean isRetry, Object obj) {
    this.isRetry = isRetry;
    this.obj = obj;
  }

  public static RetryResult ofResult(Boolean isRetry, Object obj) {
    return new RetryResult(isRetry, obj);
  }

  public static RetryResult ofResult(Boolean isRetry) {
    return new RetryResult(isRetry, null);
  }

  public Boolean isRetry() {
    return isRetry;
  }

  public Object getObj() {
    return obj;
  }
}
