package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskThreeDSResult {

    /**
     * The version of 3D Secure protocol
     */
    private String threeDSVersion;

    /**
     * Indicates the type of user interactions during 3DS 2.0 authentication
     */
    private String threeDSInteractionMode;

    /**
     * Electronic Commerce Indicator (ECI) that is returned by the card scheme
     */
    private String eci;

    /**
     * The cardholder authentication value
     */
    private String cavv;

}