package com.alipay.global.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {

  private String resultCode;
  private ResultStatusType resultStatus;
  private String resultMessage;
}
