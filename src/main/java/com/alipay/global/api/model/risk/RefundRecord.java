/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.Amount;

import java.util.Date;

/**
 * Refund history for this transaction.
 * 本次交易的退款记录。
 */
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
    private Date   refundTime;

    public String getReferenceOrderId() {
        return referenceOrderId;
    }

    public void setReferenceOrderId(String referenceOrderId) {
        this.referenceOrderId = referenceOrderId;
    }

    public String getReferenceGoodsId() {
        return referenceGoodsId;
    }

    public void setReferenceGoodsId(String referenceGoodsId) {
        this.referenceGoodsId = referenceGoodsId;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }
}