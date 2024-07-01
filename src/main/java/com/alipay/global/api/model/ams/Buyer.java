package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Buyer {

    private String referenceBuyerId;
    private UserName buyerName;
    private String buyerPhoneNo;
    private String buyerEmail;
    private String buyerRegistrationTime;

    private Boolean isAccountVerified;

    private Integer successfulOrderCount;

}
