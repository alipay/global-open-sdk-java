package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayBillingSubscriptionNotify extends AlipayNotify {

  /** The merchant request ID. */
  private String merchantRequestId;

  /** The event time. ISO 8601 format string. */
  private String eventTime;

  /** The subscription ID. */
  private String subscriptionId;

  /** The invoice ID associated with the subscription. */
  private String invoiceId;

  /** The subscription status. */
  private String status;

  /** The reason for the status change. */
  private String reason;

  /** The description of the reason. */
  private String reasonDescription;

  /** The previous subscription status. */
  private String previousStatus;

  /** The fixed subscription amount per period. Returned when subscription has fixed pricing and subscriptionNotificationType is CREATE. */
  private Amount fixedAmount;
}
