package com.alipay.global.api.model.ams;

import java.util.List;
import lombok.*;

/**
 * Full credit note object for notifyCreditNote. Contains status, amounts, refund details, and line
 * items.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditNoteInfo {

  /** The credit note ID. Maximum length: 64 characters. */
  private String creditNoteId;

  /**
   * The credit note type. Valid values: POST_PAYMENT, PRE_PAYMENT. Maximum length: 32 characters.
   */
  private String type;

  /**
   * The current status. Valid values: ISSUED, PROCESSING, REFUNDED, REFUND_FAILED, VOIDED. Maximum
   * length: 32 characters.
   */
  private String status;

  /** The total credit note amount. */
  private Amount totalAmount;

  /** The refund amount to the original payment method. Present when type is POST_PAYMENT. */
  private Amount refundAmount;

  /**
   * The refund tracking status. Present when type is POST_PAYMENT and refund occurred. Maximum
   * length: 32 characters.
   */
  private String refundStatus;

  /**
   * The refund transaction ID in the payment system. Present when type is POST_PAYMENT and refund
   * occurred. Maximum length: 64 characters.
   */
  private String refundId;

  /**
   * The refund destination. Valid values: REFUND, CREDIT_BALANCE. Maximum length: 32 characters.
   */
  private String refundDestination;

  /** The structured reason code. Maximum length: 32 characters. */
  private String reason;

  /** The free-text description of the reason. Maximum length: 512 characters. */
  private String reasonDescription;

  /** The customer-visible memo shown on the credit note PDF. Maximum length: 512 characters. */
  private String memo;

  /** The effective date of the credit note. ISO 8601 format. Maximum length: 29 characters. */
  private String effectiveDate;

  /** The issuance timestamp. ISO 8601 format. Maximum length: 29 characters. */
  private String issuedAt;

  /**
   * The refund timestamp. Present when status is REFUNDED. ISO 8601 format. Maximum length: 29
   * characters.
   */
  private String refundedAt;

  /**
   * The void timestamp. Present when status is VOIDED. ISO 8601 format. Maximum length: 29
   * characters.
   */
  private String voidedAt;

  /** The creation timestamp. ISO 8601 format. Maximum length: 29 characters. */
  private String createdAt;

  /** The line items. Maximum 100 elements. */
  private List<LineItem> items;
}
