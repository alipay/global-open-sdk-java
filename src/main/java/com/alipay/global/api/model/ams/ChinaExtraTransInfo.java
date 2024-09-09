package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChinaExtraTransInfo {

    /**
     * value of BusinessType
     */
    private String businessType;
    private String flightNumber;
    private String departureTime;
    private String hotelName;
    private String checkinTime;
    private String checkoutTime;
    private String admissionNoticeUrl;
    private String totalQuantity;
    private String goodsInfo;
    private String otherBusinessType;

}
