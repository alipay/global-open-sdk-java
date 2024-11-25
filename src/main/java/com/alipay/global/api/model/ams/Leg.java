package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Leg {
    private String departureTime;
    private String arrivalTime;
    private Address departureAddress;
    private Address arrivalAddress;
    private String carrierName;
    private String carrierNo;
    private ClassType classType;
    private String departureAirportCode;
    private String arrivalAirportCode;
}
