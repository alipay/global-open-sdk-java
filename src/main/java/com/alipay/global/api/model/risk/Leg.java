package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Leg {
    private Date departureTime;
    private Date arrivalTime;
    private Address departureAddress;
    private Address arrivalAddress;
    private String carrierName;
    private String carrierNo;
    private String classType;
}
