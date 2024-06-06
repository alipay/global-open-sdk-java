package com.alipay.global.api.model.aps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String region;
    private String state;
    private String city;
    private String address1;
    private String address2;
    private String zipCode;

}
