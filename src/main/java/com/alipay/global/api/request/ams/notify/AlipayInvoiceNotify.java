package com.alipay.global.api.request.ams.notify;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInvoiceNotify extends AlipayNotify {

  /** The merchant request ID. */
  private String merchantRequestId;

  /** The event time. ISO 8601 format string. */
  private String eventTime;

  /** The invoice ID. */
  private String invoiceId;

  /** The subscription ID associated with the invoice. */
  private String subscriptionId;

  /** The customer ID. */
  private String customerId;

  /** The invoice status. */
  private String status;

  /** The previous invoice status. */
  private String previousStatus;

  /** The reason for the status change. */
  private String reason;

  /** The description of the reason. */
  private String reasonDescription;
}
