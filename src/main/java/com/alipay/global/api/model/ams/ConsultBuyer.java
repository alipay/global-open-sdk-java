package com.alipay.global.api.model.ams;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** Consult-only Buyer DTO with the optional Kwai-Bettr string flag. */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ConsultBuyer extends Buyer {
  /** Kwai-Bettr consult flag: string "true" or "false"; other merchants omit it. */
  private String buyerAccessGroupFlag;

  public ConsultBuyer(
      List<String> preferredLocales,
      String businessName,
      String buyerType,
      String referenceBuyerId,
      UserName buyerName,
      String buyerPhoneNo,
      String buyerEmail,
      String buyerRegistrationTime,
      Boolean isAccountVerified,
      Integer successfulOrderCount,
      String buyerPhoneNoContryCode,
      Amount successfulOrderAmount,
      String dateOfLastPaidPurchase,
      String dateOfFirstPaidPurchase,
      List<BuyerTaxId> taxIds,
      Address businessAddress,
      String buyerAccessGroupFlag) {
    super(
        preferredLocales,
        businessName,
        buyerType,
        referenceBuyerId,
        buyerName,
        buyerPhoneNo,
        buyerEmail,
        buyerRegistrationTime,
        isAccountVerified,
        successfulOrderCount,
        buyerPhoneNoContryCode,
        successfulOrderAmount,
        dateOfLastPaidPurchase,
        dateOfFirstPaidPurchase,
        taxIds,
        businessAddress);
    this.buyerAccessGroupFlag = buyerAccessGroupFlag;
  }
}
