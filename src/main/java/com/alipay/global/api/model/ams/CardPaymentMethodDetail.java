package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardPaymentMethodDetail {

    private String cardToken;
    private String cardNo;
    private CardBrand brand;
    private CardBrand selectedCardBrand;
    private String cardIssuer;
    private String countryIssue;
    private UserName instUserName;
    private String expiryYear;
    private String expiryMonth;
    private Address billingAddress;
    private String mask;
    private String last4;
    private String paymentMethodDetailMetadata;

    private String maskedCardNo;

    private String fingerprint;

    private String authenticationFlow;

    private String funding;

    private String avsResultRaw;

    private String cvvResultRaw;

    private String bin;

    private String issuerName;

    private String issuingCountry;

    private String lastFour;

    private UserName cardholderName;

    private String cvv;

    private String dateOfBirth;

    private String businessNo;

    private String cardPasswordDigest;

    private String cpf;

    private String payerEmail;

    private String networkTransactionId;

    private Boolean is3DSAuthentication;

}
