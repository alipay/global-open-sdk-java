package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResultInfo {

    /**
     * The masked card number, which just shows part of the card number and can be used to display to the user
     */
    private String cardNo;

    /**
     * The card brand, which can be used to display to the user
     */
    private String cardBrand;

    /**
     * The token of the card, the value of this parameter is used by paymentMethodId in the pay
     */
    private String cardToken;

    /**
     * The issuing country of the card
     */
    private String issuingCountry;

    /**
     * The funding type of the card.
     */
    private String funding;

    /**
     * The region code that represents the country or region of the payment method
     */
    private String paymentMethodRegion;

    /**
     * The result of 3D Secure authentication
     */
    private ThreeDSResult threeDSResult;

    /**
     * The raw AVS result. See AVS result codes to check the valid values
     */
    private String avsResultRaw;

    /**
     * The raw Card Verification Value (CVV), Card Security Code (CSC), or Card Verification Code (CVC) result
     */
    private String cvvResultRaw;

    /**
     * The unique ID assigned by the card scheme to identify a transaction. The value of this parameter is used by the same parameter of pay (Cashier Payment) request in subsequent payments
     */
    private String networkTransactionId;

    /**
     * The installment plan information for an installment payment
     */
    private CreditPayPlan creditPayPlan;

    private String cardholderName;

    private String cardBin;

    private String lastFour;

    private String expiryMonth;

    private String expiryYear;

}
