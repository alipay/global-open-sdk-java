package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    private String referenceMerchantId;
    private String merchantMCC;
    private String merchantName;
    private String merchantDisplayName;
    private Address merchantAddress;
    private String merchantRegisterDate;
    private Store store;
    private MerchantType merchantType;

}
