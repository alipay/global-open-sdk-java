package com.alipay.global.api.model.ams;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessInfo {

    private String        mcc;
    private List<WebSite> websites;
    private String        englishName;
    private String        doingBusinessAs;
    private String        mainSalesCountry;
    private String        appName;
    private String        serviceDescription;
}
