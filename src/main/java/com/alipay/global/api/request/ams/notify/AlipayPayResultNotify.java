package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.*;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayPayResultNotify extends AlipayNotify {

  /** The unique ID that is assigned by a merchant to identify a payment request. */
  private String paymentRequestId;

  /** unique id generated from ipay for this payment */
  private String paymentId;

  /** amount of this payment */
  private Amount paymentAmount;

  /** create time of this payment */
  private String paymentCreateTime;

  /** the time of payment finish */
  private String paymentTime;

  /** The total amount for customs declaration */
  private Amount customsDeclarationAmount;

  /**
   * The value of this field equals to transaction amount multiplied by the value of
   * settlementQuote. This field is returned when the currency exchange is predetermined and the
   * exchange rate is locked at the time of transaction
   */
  private Amount grossSettlementAmount;

  /**
   * The exchange rate between the settlement currency and transaction currency. This field is
   * returned when grossSettlementAmount is returned
   */
  private Quote settlementQuote;

  /** Information about the customer of Alipay+ Mobile Payment Provider (Alipay+ MPP) */
  private PspCustomerInfo pspCustomerInfo;

  /** The unique ID assigned by the non-Alipay acquirer for the transaction */
  private String acquirerReferenceNo;

  /** The payment result information */
  private PaymentResultInfo paymentResultInfo;

  /** The information of the acquirer that processes the payment. */
  private AcquirerInfo acquirerInfo;

  private List<PromotionResult> promotionResult;

  private String paymentMethodType;

  private CustomizedInfo customizedInfo;
  private Quote paymentQuote;
  private Amount processingAmount;

  /** 用于商户自定义元数据信息，支持JSON格式 */
  private String metadata;

  private String subscriptionOrderId;
  private RetryInfo retryInfo;

  /** The updateAmount request ID. Returned when notifyType=UPDATE_AMOUNT_RESULT. */
  private String updateRequestId;

  /**
   * The absolute expiry time of this pre-authorization. Returned when auth is successful. Multiple
   * updateAmount calls do not reset this value. ISO 8601 format string.
   */
  private String authExpiryTime;

  /**
   * The tax calculation ID associated with the payment. Merchants should retain this ID for
   * reconciliation and subsequent refunds; tax details can be queried through the
   * inquireTransactionList API. This field only indicates a tax association and does not indicate
   * that tax has been posted or recorded. If absent, the payment is not subject to tax.
   */
  private String taxCalculationId;

  /**
   * The status of the post-authorization manual review. Valid values are: PROCESSING: The manual
   * review is not completed. Do not capture the payment or fulfill the order before the review
   * reaches a final state. ACCEPT: The manual review is passed. REJECT: The manual review is
   * rejected. Note: This field is returned when the channel authorization requires manual review,
   * or when the value of popRiskDecisionResultInfo.postRiskDecision is REVIEW. When this field is
   * returned, authReviewSource is returned at the same time.
   */
  private String authReviewStatus;

  /**
   * The source of the post-authorization risk review. Valid values are: ANTOM_SHIELD: The review is
   * initiated by the Antom internal risk engine (Antom Shield). PSP: The review is initiated by the
   * acquirer-side risk control. Note: This field is returned only when authReviewStatus is
   * returned. When both the channel manual review and the Antom Shield review are required, the
   * channel manual review takes precedence and this field returns PSP.
   */
  private String authReviewSource;

  /** The post-authorization risk review result of the payment. */
  private PopRiskDecisionResultInfo popRiskDecisionResultInfo;
}
