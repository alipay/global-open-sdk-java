package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.RefundFromMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayDirectRefundNotify extends AlipayNotify {

  /**
   * The refund status:
   * SUCCESS/PROCESSING/REFUND_AMOUNT_EXCEED/MERCHANT_BALANCE_NOT_ENOUGH/PROCESS_FAIL/PARAM_ILLEGAL/REFUND_WINDOW_EXCEED/ACCESS_DENIED/INVALID_ACCESS_TOKEN
   */
  private String refundStatus;

  /** The unique ID assigned by Alipay to identify a refund */
  private String refundId;

  /** The unique ID assigned by Alipay to identify a payment */
  private String paymentId;

  /** The unique ID assigned by a merchant to identify a refund request */
  private String refundRequestId;

  /** The date and time when the refund reaches a final state of success or failure */
  private String refundTime;

  /** The refund from method information */
  private RefundFromMethod refundFromMethod;

  /** The refund to amount */
  private Amount refundToAmount;

  /** The refund from amount */
  private Amount refundFromAmount;
}
