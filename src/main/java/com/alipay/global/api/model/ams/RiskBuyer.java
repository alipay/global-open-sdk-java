package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskBuyer {
    /**
     * The buyer's note to a merchant.
     */
    private String noteToMerchant;

    /**
     * The buyer's note to a deliveryman or a take-out rider.
     */
    private String noteToShipping;

    /**
     * The successful orders the buyer made within the last one hour.
     */
    private Integer orderCountIn1H;

    /**
     * The successful orders the buyer made within the last 24 hour.
     */
    private Integer orderCountIn24H;

}