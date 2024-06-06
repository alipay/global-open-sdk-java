package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskAddress {
    /**
     * The type of the receiver's phone number
     */
    private String shippingPhoneType;

    /**
     * Indicates whether the billing state is the same as the shipping state
     */
    private Boolean isBillShipStateSame;

    /**
     * Indicates whether a previous billing state is the same as the shipping state
     */
    private Boolean isPreviousStateSame;

    /**
     * The distance in meters between the buyer's location and their shipping address.
     */
    private Integer locToShipDistance;

    /**
     * The minimum distance in meters between the buyer's previous shipping address and their billing address.
     */
    private Integer minPreviousShipToBillDistance;

}