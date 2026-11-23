package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.EventMerchantInfo;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * One flat ABA VCC card transaction event callback (notification profile v2.1, document v2.13).
 * Verify the raw-body signature before parsing. Durable acceptance and eventId deduplication belong
 * to the merchant callback handler. Amount values remain quoted minor-unit strings (up to 20
 * digits).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlipayCardTransactionEventNotify {

  /** Stable event ID and the only callback idempotency key. Required; maximum 64 characters. */
  private String eventId;

  /** Spend-group ID shared with inquiry. Required; maximum 64 characters. */
  private String lifecycleId;

  /**
   * Required event type: AUTH, AUTH_CANCEL, CAPTURE, REFUND, CHARGEBACK or REPAYMENT. Preserve
   * unknown future values.
   */
  private String eventType;

  /**
   * Optional ISO 8601 time of the channel-correlated authorization, maximum 32 characters. Omitted
   * for AUTH or when unavailable; never inferred from transactionTime or authExpireTime.
   */
  private String authTime;

  /**
   * Optional ISO 8601 authorization expiry time, maximum 32 characters. Sent only for AUTH when
   * available.
   */
  private String authExpireTime;

  /** Optional AUTH or PRE_AUTH, maximum 32 characters. Sent only for AUTH when available. */
  private String authType;

  /** Optional network approval code, maximum 64 characters. Sent only for AUTH when available. */
  private String authCode;

  /**
   * Optional Antom-normalized failure code, maximum 64 characters. Sent only for FAIL when a mapped
   * code exists.
   */
  private String failureReason;

  /**
   * Required terminal status: SUCCESS or FAIL. CAPTURE is SUCCESS-only. The accepted status never
   * changes.
   */
  private String status;

  /**
   * ANTOM_BIZ_ACCOUNT or ANTOM_FUND_ACCOUNT, maximum 32 characters. Required with a directional
   * amount; CAPTURE sends ANTOM_BIZ_ACCOUNT.
   */
  private String balanceType;

  /** Required business-event time in ISO 8601 format with timezone; maximum 32 characters. */
  private String transactionTime;

  /**
   * Required CARD_PAYMENT for AUTH, CAPTURE and REPAYMENT, or CARD_REFUND for AUTH_CANCEL, REFUND
   * and CHARGEBACK.
   */
  private String billType;

  /**
   * Required for successful AUTH, CAPTURE and REPAYMENT. CAPTURE carries value "0" and billing
   * currency, not another debit; omitted otherwise.
   */
  private Amount outAmount;

  /**
   * Optional billing-currency inflow for successful AUTH_CANCEL, REFUND or CHARGEBACK when
   * available. Omitted for CAPTURE.
   */
  private Amount inAmount;

  /**
   * Optional BASE/QUOTE currency pair, maximum 8 characters. Sent with exchangeRate for a
   * cross-currency transaction when both are available.
   */
  private String exchangeCurrencyPair;

  /**
   * Optional positive quoted decimal string, maximum 32 characters. One major BASE unit equals this
   * many QUOTE units; paired with exchangeCurrencyPair.
   */
  private String exchangeRate;

  /**
   * Required current-event amount in original transaction currency. For CAPTURE this is the
   * captured amount, not the zero additional outflow.
   */
  private Amount transactionAmount;

  /** Required merchant-owned card asset ID; maximum 32 characters. */
  private String assetId;

  /** Optional PCI-safe card mask exposing at most the last four digits; maximum 32 characters. */
  private String maskedCardNo;

  /**
   * Optional original-transaction program code matching ^[A-Z0-9]{3}$. Extensible string, not a
   * fixed enum or rebate rate; later card-profile changes do not rewrite it.
   */
  private String cardProduct;

  /**
   * Optional approved merchant context. Name maximum 256 characters, region exactly 2 and mcc
   * exactly 4; parent omitted when no child is available.
   */
  private EventMerchantInfo merchantInfo;

  /**
   * Optional approved context: at most 30 entries, keys at most 32 characters, values at most 128
   * and serialized JSON at most 3096. No key is approved in notification profile v2.1, so it is
   * omitted.
   */
  private Map<String, String> metadata;
}
