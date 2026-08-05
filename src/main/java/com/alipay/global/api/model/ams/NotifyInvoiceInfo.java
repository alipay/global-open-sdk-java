package com.alipay.global.api.model.ams;

import lombok.*;

/** Invoice summary object embedded in notifyCreditNote. Provides invoice context and amount breakdown. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotifyInvoiceInfo {

  /** The invoice ID adjusted by this credit note. Maximum length: 64 characters. */
  private String invoiceId;

  /** The invoice status at the time of the credit note event. Valid values: OPEN, PAID, VOID, UNCOLLECTIBLE, DRAFT. Maximum length: 32 characters. */
  private String invoiceStatus;

  /** The original invoice total amount before any credit notes. Immutable since invoice finalization. */
  private Amount originalAmount;

  /** The cumulative amount of all PRE_PAYMENT credit notes applied to this invoice. */
  private Amount prePaymentCreditNotesAmount;

  /** The cumulative refunded amount of all POST_PAYMENT credit notes (excluding voided ones). */
  private Amount postPaymentCreditNotesAmount;

  /** The current invoice total after all PRE_PAYMENT credit note adjustments. */
  private Amount adjustedAmount;
}
