package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.ErrorEvent;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayMeterEventNotify extends AlipayNotify {

  /** The event name. */
  private String eventName;

  /** The error event details. */
  private List<ErrorEvent> errorEvents;
}
