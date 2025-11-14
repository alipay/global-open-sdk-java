package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.Result;
import lombok.Data;

@Data
public class AlipayNotify {

  private String notifyType;

  private Result result;
}
