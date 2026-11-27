package com.alipay.global.api.model.ams;

import lombok.*;

/**
 * Invoice summary object embedded in notifyCreditNote. Provides invoice context and amount
 * breakdown.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotifyInvoiceInfo {

  /** The invoice ID adjusted by this credit note. Maximum length: 64 characters. */
  private String invoiceId;

  /**
   * The invoice status at the time of the credit note event. Valid values: OPEN, PAID, VOID,
   * UNCOLLECTIBLE, DRAFT. Maximum length: 32 characters.
   */
  private String invoiceStatus;

  /**
   * The original invoice total amount before any credit notes. Immutable since invoice
   * finalization.
   */
  private Amount originalAmount;

  /** Cumulative non-voided PRE_PAYMENT amount; reduces invoice total. Zero when none. */
  private Amount prePaymentCreditNotesAmount;

  /**
   * Cumulative non-voided POST_PAYMENT amount, including processing/failed refunds until void. Does
   * not change invoice total and is not the successfully-refunded amount.
   */
  private Amount postPaymentCreditNotesAmount;

  /**
   * Current invoice total after PRE_PAYMENT reductions, equal to current totalAmount. POST_PAYMENT
   * refunds do not change it; zero may transition the invoice to PAID.
   */
  private Amount adjustedAmount;
}
