package com.alipay.global.api.model.ams;

import lombok.*;

/** ErrorEvent */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorEvent {

  /** The error code. */
  private String errorCode;

  /** The idempotency key. Maximum length: 128 characters. */
  private String idempotencyKey;

  /** The original event timestamp, 13-digit millisecond timestamp. */
  private Long eventTimestamp;

  private EventPayload payload;
}
