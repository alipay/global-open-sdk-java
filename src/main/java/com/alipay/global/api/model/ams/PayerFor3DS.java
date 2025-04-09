package com.alipay.global.api.model.ams;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayerFor3DS {
    private String accountCreateDate;
    private String accountAgeIndicator;
    private String accountChangeDate;
    private String accountChangeIndicator;
    private String accountPasswordChangeDate;
    private String accountPasswordChangeIndicator;
    private String paymentAccountCreateDate;
    private String paymentAccountAgeIndicator;
    private String suspiciousAccountActivity;
    private String purchaseCountLast6Months;
    private String transactionCountLast24Hours;
    private String transactionCountLastYear;
    private String provisionAttemptCountLast24Hours;
    private String shippingAddressCreateDate;
    private String shippingAddressAgeIndicator;
    private String shippingNameIndicator;
    private Contact contact;

}
