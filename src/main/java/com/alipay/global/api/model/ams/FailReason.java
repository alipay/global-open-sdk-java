package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailReason {
  private String errorCode;
  private String errorDesc;
}
