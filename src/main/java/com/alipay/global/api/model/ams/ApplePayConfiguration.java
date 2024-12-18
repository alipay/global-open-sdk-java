package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplePayConfiguration {
    private List<String> requiredBillingContactFields;
    private List<String> requiredShippingContactFields;
    private Boolean buttonsBundled;
    private String applePayToken;

}
