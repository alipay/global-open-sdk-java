package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {

    private UserName shippingName;
    private Address shippingAddress;
    private String shippingCarrier;
    private String shippingPhoneNo;

    private String shipToEmail;

    private String shippingFeeId;
    private Amount shippingFee;
    private String shippingDescription;
    private DeliveryEstimate deliveryEstimate;

}
