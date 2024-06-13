package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardVerificationResult {
    /**
     * Authentication type
     */
    private String authenticationType;

    /**
     * The authentication method that a merchant uses for a card payment
     */
    private String authenticationMethod;

    /**
     * The verification result of the card verification value (CVV)
     */
    private String cvvResult;

    /**
     * The address verification result.
     */
    private String avsResult;

    /**
     * The authorization code granted by the payment method provider for a successful 3D authentication.
     */
    private String authorizationCode;

    /**
     * The result of 3D Secure authentication.
     */
    private RiskThreeDSResult threeDSResult;

}