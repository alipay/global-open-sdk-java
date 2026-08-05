package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.InvoicePaymentInfo;
import com.alipay.global.api.model.ams.SubscriptionInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayInvoiceNotify extends AlipayNotify {

  /** The unique ID assigned by the merchant to identify the invoice creation request. Maximum length: 64 characters. */
  private String invoiceRequestId;

  /** The unique ID assigned by Antom to identify the invoice. Maximum length: 64 characters. */
  private String invoiceId;

  /** The current invoice status after the payment event. Valid values: PAID, UNPAID, VOID, UNCOLLECTIBLE. Maximum length: 32 characters. */
  private String invoiceStatus;

  /** The total invoice amount. */
  private Amount invoiceAmount;

  /** Payment result details for the invoice payment attempt. Null when no payment was attempted (e.g., VOID from DRAFT). */
  private InvoicePaymentInfo paymentInfo;

  /** Associated subscription details. Present when invoice is linked to a subscription. Null for one-time payment invoices. */
  private SubscriptionInfo subscription;

  /** The customer ID associated with the invoice. Maximum length: 64 characters. */
  private String customerId;

  /** Machine-readable reason for status change. Maximum length: 64 characters. */
  private String reason;

  /** Human-readable description of the reason. Valid values: PAYMENT_SUCCESS, PAYMENT_FAILED, PAYMENT_TIMEOUT, MANUAL_VOID, MANUAL_MARK_UNCOLLECTIBLE. Maximum length: 64 characters. */
  private String reasonDescription;
}
