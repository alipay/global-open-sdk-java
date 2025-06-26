/*
 * Ant Group
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.alipay.global.api.model.constants;

/**
 * @author zhangtianren
 * @version AntomPathConstants.java, v 0.1 2024年06月13日 下午4:20 zhangtianren
 */
public class AntomPathConstants {

  /** see <a href="https://global.alipay.com/docs/ac/ams/authconsult">auth consult</a> */
  public static final String AUTH_CONSULT_PATH = "/ams/api/v1/authorizations/consult";

  /** see <a href="https://global.alipay.com/docs/ac/ams/accesstokenapp">auth apply token</a> */
  public static final String AUTH_APPLY_TOKEN_PATH = "/ams/api/v1/authorizations/applyToken";

  /** see <a href="https://global.alipay.com/docs/ac/ams/authrevocation">auth revoke</a> */
  public static final String AUTH_REVOKE_PATH = "/ams/api/v1/authorizations/revoke";

  /**
   * see <a href="https://global.alipay.com/docs/ac/ams/vaultingsession">create vaulting session</a>
   */
  public static final String CREATE_VAULTING_SESSION_PATH =
      "/ams/api/v1/vaults/createVaultingSession";

  /** see <a href="https://global.alipay.com/docs/ac/ams/vault_method">vault payment method</a> */
  public static final String VAULT_PAYMENT_METHOD_PATH = "/ams/api/v1/vaults/vaultPaymentMethod";

  /** see <a href="https://global.alipay.com/docs/ac/ams/inquire_vaulting">inquire vaulting</a> */
  public static final String INQUIRE_VAULTING_PATH = "/ams/api/v1/vaults/inquireVaulting";

  /**
   * see <a href="https://global.alipay.com/docs/ac/oneapi/inquire_token">inquireToken vaulting</a>
   */
  public static final String INQUIRE_TOKEN_VAULTING_PATH = "/ams/api/v1/vaults/inquireToken";

  /**
   * see <a href="https://global.alipay.com/docs/ac/oneapi/update_token">updateToken vaulting</a>
   */
  public static final String UPDATE_TOKEN_VAULTING_PATH = "/ams/api/v1/vaults/updateToken";

  /**
   * see <a href="https://global.alipay.com/docs/ac/oneapi/delete_token">deleteToken vaulting</a>
   */
  public static final String DELETE_TOKEN_VAULTING_PATH = "/ams/api/v1/vaults/deleteToken";

  /** see <a href="https://global.alipay.com/docs/ac/ams/consult">consult</a> */
  public static final String CONSULT_PAYMENT_PATH = "/ams/api/v1/payments/consult";

  /**
   * see <a href="https://global.alipay.com/docs/ac/ams/payment_cashier">pay (Checkout Payment)</a>
   * or <a href="https://global.alipay.com/docs/ac/ams/payment_agreement">pay (Auto Debit)</a>
   */
  public static final String PAYMENT_PATH = "/ams/api/v1/payments/pay";

  /** see <a href="https://global.alipay.com/docs/ac/oneapi/fetch">fetchNonce </a> */
  public static final String PAYMENT_FETCH_NONCE_PATH = "/ams/api/v1/payments/fetchNonce";

  /**
   * see <a href="https://global.alipay.com/docs/ac/oneapi/inquireInstallment">inquireInstallment
   * </a>
   */
  public static final String PAYMENT_INQUIRE_INSTALLMENT_PATH =
      "/ams/api/v1/payments/inquireInstallment";

  /**
   * see <a href="https://global.alipay.com/docs/ac/oneapi/inquireExchangeRate">inquireExchangeRate
   * </a>
   */
  public static final String PAYMENT_INQUIRE_EXCHANGE_RATE_PATH =
      "/ams/api/v1/payments/inquireExchangeRate";

  /**
   * see <a href="https://global.alipay.com/docs/ac/ams/session_cashier">create payment session</a>
   * or <a href="https://global.alipay.com/docs/ac/ams/createpaymentsession_easypay">create payment
   * session</a>
   */
  public static final String CREATE_SESSION_PATH = "/ams/api/v1/payments/createPaymentSession";

  /** see <a href="https://global.alipay.com/docs/ac/ams/capture">capture</a> */
  public static final String CAPTURE_PATH = "/ams/api/v1/payments/capture";

  /** see <a href="https://global.alipay.com/docs/ac/ams/paymentri_online">inquiry payment</a> */
  public static final String INQUIRY_PAYMENT_PATH = "/ams/api/v1/payments/inquiryPayment";

  /** see <a href="https://global.alipay.com/docs/ac/ams/paymentc_online">cancel</a> */
  public static final String CANCEL_PATH = "/ams/api/v1/payments/cancel";

  public static final String SYNC_ARREAR_PATH = "/ams/api/v1/payments/syncArrear";

  public static final String CREATE_DEVICE_CERTIFICATE_PATH =
      "/ams/api/v1/payments/createDeviceCertificate";

  /** see <a href="https://global.alipay.com/docs/ac/ams/create_sub">create subscription</a> */
  public static final String SUBSCRIPTION_CREATE_PATH = "/ams/api/v1/subscriptions/create";

  /** see <a href="https://global.alipay.com/docs/ac/ams/change_sub">change subscription</a> */
  public static final String SUBSCRIPTION_CHANGE_PATH = "/ams/api/v1/subscriptions/change";

  /** see <a href="https://global.alipay.com/docs/ac/ams/cancel_sub">cancel subscription</a> */
  public static final String SUBSCRIPTION_CANCEL_PATH = "/ams/api/v1/subscriptions/cancel";

  public static final String SUBSCRIPTION_UPDATE_PATH = "/ams/api/v1/subscriptions/update";

  public static final String SUBSCRIPTION_INQUIRE_PATH = "/ams/api/v1/payments/inquire";

  /** see <a href="https://global.alipay.com/docs/ac/ams/accept">accept dispute</a> */
  public static final String ACCEPT_DISPUTE_PATH = "/ams/api/v1/payments/acceptDispute";

  /**
   * see <a href="https://global.alipay.com/docs/ac/ams/supply_evidence">supply defense document</a>
   */
  public static final String SUPPLY_DEFENCE_DOC_PATH = "/ams/api/v1/payments/supplyDefenseDocument";

  /** see <a href="https://global.alipay.com/docs/ac/ams/download">download dispute evidence</a> */
  public static final String DOWNLOAD_DISPUTE_EVIDENCE_PATH =
      "/ams/api/v1/payments/downloadDisputeEvidence";

  /** see <a href="https://global.alipay.com/docs/ac/ams/refund_online">refund</a> */
  public static final String REFUND_PATH = "/ams/api/v1/payments/refund";

  /** see <a href="https://global.alipay.com/docs/ac/ams/ir_online">inquiry refund</a> */
  public static final String INQUIRY_REFUND_PATH = "/ams/api/v1/payments/inquiryRefund";

  /** see <a href="https://global.alipay.com/docs/ac/ams/declare">declare</a> */
  public static final String DECLARE_PATH = "/ams/api/v1/customs/declare";

  /** see <a href="https://global.alipay.com/docs/ac/ams/inquirydeclare">inquiry declare</a> */
  public static final String INQUIRY_DECLARE_PATH =
      "/ams/api/v1/customs/inquiryDeclarationRequests";

  public static final String MARKETPLACE_SUBMITATTACHMENT_PATH =
      "/ams/api/open/openapiv2_file/v1/business/attachment/submitAttachment";

  public static final String MARKETPLACE_REGISTER_PATH = "/ams/api/v1/merchants/register";

  public static final String MARKETPLACE_SETTLEMENTINFO_UPDATE_PATH =
      "/ams/api/v1/merchants/settlementInfo/update";

  public static final String MARKETPLACE_INQUIREBALANCE_PATH =
      "/ams/api/v1/accounts/inquireBalance";

  public static final String MARKETPLACE_SETTLE_PATH = "/ams/api/v1/payments/settle";

  public static final String MARKETPLACE_CREATEPAYOUT_PATH = "/ams/api/v1/funds/createPayout";

  public static final String MARKETPLACE_CREATETRANSFER_PATH = "/ams/api/v1/funds/createTransfer";

  public static final String RISK_DECIDE_PATH = "/ams/api/v1/risk/payments/decide";

  public static final String RISK_REPORT_PATH = "/ams/api/v1/risk/payments/reportRisk";

  public static final String RISK_SEND_PAYMENT_RESULT_PATH =
      "/ams/api/v1/risk/payments/sendPaymentResult";

  public static final String RISK_SEND_REFUND_RESULT_PATH =
      "/ams/api/v1/risk/payments/sendRefundResult";
}
