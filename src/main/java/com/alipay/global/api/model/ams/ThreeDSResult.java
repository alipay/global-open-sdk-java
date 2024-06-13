package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreeDSResult {

    /**
     * The version of 3D Secure protocol
     */
    private String threeDSVersion;

    /**
     * Electronic Commerce Indicator (ECI) that is returned by the card scheme
     */
    private String eci;

    /**
     * The cardholder authentication value
     */
    private String cavv;

    /**
     * dsTransactionId
     */
    private String dsTransactionId;

    /**
     * The unique transaction identifier assigned by the Directory Server (DS) for 3D Secure authentication
     */
    private String xid;

}
