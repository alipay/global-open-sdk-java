package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEstimate {

    private DeliveryEstimateInfo minimum;

    private DeliveryEstimateInfo maximum;
}
