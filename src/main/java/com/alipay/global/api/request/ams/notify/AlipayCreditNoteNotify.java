package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.CreditNoteInfo;
import com.alipay.global.api.model.ams.NotifyInvoiceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** CREDIT_NOTE_RESULT payload. Acknowledge separately with the standard Result envelope. */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCreditNoteNotify extends AlipayNotify {

  /**
   * Required merchant creation request ID, shared by all events of this credit note. Used for
   * correlation, not delivery deduplication. Maximum length: 64 characters. Delivery deduplication
   * uses the outer envelope notify_id, which is not a payload member.
   */
  private String creditNoteRequestId;

  /**
   * The lifecycle event type. Valid values: CREATED, VOIDED, REFUND_SUCCEEDED, REFUND_FAILED.
   * Maximum length: 32 characters.
   */
  private String creditNoteNotificationType;

  /** The customer ID associated with this credit note. Maximum length: 64 characters. */
  private String customerId;

  /** The notification credit note projection, excluding detail-only merchant metadata. */
  private CreditNoteInfo creditNote;

  /** The associated invoice summary providing context and full amount breakdown. */
  private NotifyInvoiceInfo invoice;
}
