package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.AcquirerInfo;
import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.PaymentResultInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayCaptureResultNotify extends AlipayNotify {

  /** The unique ID that is assigned by the merchant to identify a capture request */
  private String captureRequestId;

  /** The unique ID that is assigned by Alipay to identify a payment */
  private String paymentId;

  /** The unique ID assigned by Alipay to identify a capture */
  private String captureId;

  /** The capture amount that the merchant requests to receive in the transaction currency */
  private Amount captureAmount;

  /** The time when Alipay captures the payment */
  private String captureTime;

  /** The unique ID assigned by the non-Alipay acquirer for the transaction */
  private String acquirerReferenceNo;

  /** The information of the acquirer that processes the payment. */
  private AcquirerInfo acquirerInfo;

  private PaymentResultInfo paymentResultInfo;

  /**
   * The tax calculation ID associated with the payment. Merchants should retain this ID for
   * reconciliation and subsequent refunds; tax details can be queried through the
   * inquireTransactionList API. This field only indicates a tax association and does not indicate
   * that tax has been posted or recorded. If absent, the payment is not subject to tax.
   */
  private String taxCalculationId;
}
