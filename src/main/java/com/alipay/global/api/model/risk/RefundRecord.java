/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.Amount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundRecord {
    /**
     * A unique ID on the merchant's side that identifies the order and is assigned by the merchant who provides the service or product
     * directly to the customer.
     * 商户侧识别订单的唯一ID，由直接向买家提供服务或商品的商户分配。
     */
    private String referenceOrderId;
    /**
     * A unique ID that identifies the product.
     * 识别商品的唯一 ID。
     */
    private String referenceGoodsId;
    /**
     * The amount of the refund for the item.
     * 商品的退款金额。
     */
    private Amount amount;
    /**
     * Refund reason.
     * 退款原因
     */
    private String refundReason;
    /**
     * The date and time when the refund reached the final state of success or failure.
     * 退款达到成功或失败终态的日期和时间。
     */
    private Date refundTime;

}