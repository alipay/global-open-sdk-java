package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaultingCard {
    private String cardToken;

    private String lastFour;

    private String brand;

    private String expiryMonth;

    private String expiryYear;

    private Address billingAddress;

    private String avsResultRaw;

    private String cvvResultRaw;

    private String issuingCountry;

    private String funding;

    private String bin;

    private String issuerName;

}