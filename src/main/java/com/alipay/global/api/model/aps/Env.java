package com.alipay.global.api.model.aps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Env {

  private TerminalType terminalType;
  private OsType osType;
  private String userAgent;
  private String deviceTokenId;
  private String clientIp;
  private String storeTerminalId;
  private String cookieId;
  private String storeTerminalRequestTime;
}
