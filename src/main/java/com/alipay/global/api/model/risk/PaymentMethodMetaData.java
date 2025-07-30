/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.Address;
import com.alipay.global.api.model.ams.CardVerificationResult;
import com.alipay.global.api.model.ams.UserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodMetaData {
  /** Card number, pass in this parameter when you have a PCI competency. 卡号，当您有PCI资质时传入该参数 */
  private String cardNo;
  /**
   * The card bank identification number, which is used to identify the financial institution that
   * issued the card. It is the first 6 or 8 digits of the card number. 卡银行识别号，用于识别发卡的金融机构。它是卡号的前 6
   * 或 8 位数字。
   */
  private String cardBin;
  /** The last 4 digits of the card number. 卡号后4位 */
  private String lastFourDigits;
  /**
   * The year in which the card expires. Pass in the last two digits of the expiration year. For
   * example, if the expiration year is 2025, the parameter has a value of 25. Pass in this
   * parameter when you have a PCI competency. 该卡到期的年份。传入到期年份的最后两位数字。例如，如果到期年份是 2025 年，则该参数的值为
   * 25。当您具有 PCI 资质时传入此参数。
   */
  private String expiryYear;
  /**
   * The month in which the card expires. The last two digits of the expiration month are passed in.
   * For example, if the expiration month is February, the value of the parameter is 02. Pass in
   * this parameter when you have a PCI competency. 该卡到期的月份。传入到期月份的最后两位数字。例如，如果到期月份是二月，则该参数的值为
   * 02。当您具有 PCI 资质时传入此参数。
   */
  private String expiryMonth;
  /** The cardholder's billing address. 持卡人的账单地址。 */
  private Address billingAddress;
  /** Whether the buyer's card payment information has been stored. 是否已存储买家卡支付信息 */
  private Boolean isCardOnFile;
  /** The cardholder's name. 持卡人姓名。 */
  private UserName cardHolderName;
  /** The sender's email address. 付款人的邮箱 */
  private String payerEmail;
  /**
   * The type of funds of the card. Valid values are as follows: CREDIT: Indicates a credit card.
   * DEBIT: Debit card. 卡的资金类型。有效值为： CREDIT: 表示信用卡。 DEBIT：表示借记卡。
   */
  private String funding;
  /**
   * Specifies whether the transaction authentication type is set to 3D authentication
   * 交易认证类型是否指定为3D认证
   */
  private Boolean is3DSAuthentication;
  /** Card brand name. 卡品牌 */
  private String cardBrand;
  /** Card issuer. 发卡行 */
  private String cardIssuer;
  /**
   * The region where the card is issued. The value of this parameter is a 2-letter country code
   * that follows the ISO 3166 Country Code standard(https://www.iso.org/obp/ui/#search).
   * 卡的发行地区。此参数的值是遵循 ISO 3166 国家/地区代码 标准的 2 个字母的国家/地区代码。
   */
  private String issuingRegion;
  /**
   * The result of the verification of the card payment method. When
   * authorizationPhase=POST_AUTHORIZATION, this parameter is passed after the card payment
   * authorization is completed. 卡支付方式的验证结果。当authorizationPhase=POST_AUTHORIZATION即卡支付授权完成后传入该参数。
   */
  private CardVerificationResult cardVerificationResult;
  /** Tax ID number of individual taxpayers in Brazil. 巴西个人纳税人的税号。 */
  private String cpf;
}
