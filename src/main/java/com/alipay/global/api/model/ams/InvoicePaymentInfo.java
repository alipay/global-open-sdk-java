package com.alipay.global.api.model.ams;

import com.alipay.global.api.model.Result;
import lombok.*;

/** Payment result details for the invoice payment attempt. Reuses the notifyPayment structure. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoicePaymentInfo {

  /** Payment result details. resultStatus=S for success, F for failure. */
  private Result result;

  /** The unique ID assigned by Antom to identify the payment for this invoice. */
  private String paymentId;

  /** The payment amount. */
  private Amount paymentAmount;

  /** The date and time when the payment reached a final state of success. ISO 8601 format. */
  private String paymentTime;
}
