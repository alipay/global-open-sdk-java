package com.alipay.global.api.model.ams;

import java.util.List;
import lombok.*;

/** Credit note notification projection. Detail-only merchant metadata is excluded. */
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

  /**
   * Optional planned original-method refund amount for refund-bearing POST_PAYMENT notes.
   * Absent/null for note-only POST_PAYMENT and PRE_PAYMENT; does not prove completion.
   */
  private Amount refundAmount;

  /**
   * Optional refund progress: REFUND_INITIATED, REFUND_SUCCEEDED or REFUND_FAILED. Absent/null
   * before progress or for note-only notes. PRE_PAYMENT uses NOT_APPLICABLE where represented.
   */
  private String refundStatus;

  /**
   * Optional payment-system refund reference when obtained. Absent/null beforehand, for note-only
   * POST_PAYMENT and PRE_PAYMENT. A reference alone does not prove success.
   */
  private String refundId;

  /** Optional REFUND routing when provided at creation; null when omitted. */
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

  /** Optional line items, at most 100; the service returns [] when absent. */
  private List<NotificationLineItem> items;

  /** Notification line item projection; excludes detail-only metadata. */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class NotificationLineItem {
    private String creditNoteItemId;
    private String type;
    private String invoiceItemId;
    private String description;

    /** Quantity in quantity mode only, from 1 to 999. */
    private Integer quantity;

    /** Required C (credit) or D (debit); D itemAmount is subtracted from the credit total. */
    private String direction;

    private Amount unitAmount;
    private Amount itemAmount;
  }
}
