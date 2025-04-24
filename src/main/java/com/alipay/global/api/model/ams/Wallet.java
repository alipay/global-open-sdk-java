package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    private String accountNo;
    private UserName accountHolderName;
    private String phoneNo;
    private String email;
    private Address billingAddress;
    private String token;
    private String tokenExpiryTime;
}
