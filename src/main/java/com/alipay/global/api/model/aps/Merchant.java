package com.alipay.global.api.model.aps;

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
    private Address merchantAddress;
    private String merchantDisplayName;
    private String merchantRegisterDate;
    private Store store;

}
