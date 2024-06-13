package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo {

    /**
     * The masked card number, which just shows part of the card number and can be used to display to the user
     */
    private String cardNo;

    /**
     * The card brand
     */
    private String cardBrand;

    /**
     * The token of the card
     */
    private String cardToken;

    /**
     * The issuing country of the card
     */
    private String issuingCountry;

    /**
     * The funding type of the card
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

}
