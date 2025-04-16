package com.alipay.global.api.response.ams.vaults;

import com.alipay.global.api.model.ams.UserName;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayVaultingInquireTokenResponse extends AlipayResponse {
    private String token;
    private UserName accountHolderName;
    private String email;
    private String issuerName;
    private String issuingCountry;
    private String tokenExpiryTime;
}
