package com.alipay.global.api.model.aps;

public enum InStorePaymentScenario {

  /** User uses a payment code to pay. */
  PaymentCode,
  /** User scans an order code to pay. */
  OrderCode,

  /** User scans an entry code to pay. */
  EntryCode;
}
