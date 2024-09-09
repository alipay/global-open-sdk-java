package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PspCustomerInfo {
    private String pspName;
    private String pspCustomerId;
    private String displayCustomerId;
    private String displayCustomerName;
    private String customer2088Id;
    private String extendInfo;
}
