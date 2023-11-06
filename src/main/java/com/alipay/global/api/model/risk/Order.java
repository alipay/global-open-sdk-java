/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Goods;
import com.alipay.global.api.model.ams.Shipping;

import java.util.List;

/**
 * Order information, such as merchant, item, amount, and shipping information.
 */
public class Order {
    /**
     * A unique ID on the merchant's side that identifies the order and is assigned by the merchant who provides the service or product
     * directly to the customer.
     * 商户侧识别订单的唯一ID，由直接向买家提供服务或商品的商户分配。
     */
    private String      referenceOrderId;
    /**
     * The amount of the merchant's order
     * 商户侧订单金额
     */
    private Amount      orderAmount;
    /**
     * Product information, including the ID, name, price, quantity, etc. of the items in the order.
     * 商品信息，包括订单中商品的ID、名称、价格、数量等。
     */
    private List<Goods> goods;
    /**
     * Shipping information, including consignee's information (name, phone number, email, and shipping address) and delivery service
     * provider information.
     * 送货信息，包括收货人的信息（姓名、电话号码、电子邮件和送货地址）和送货服务提供商的信息。
     */
    private Shipping    shipping;
    /**
     * Secondary business information, if you are an acquirer with a second-tier merchant, pass this field.
     * 二级商户信息，如果您是拥有二级商户的收单机构，请传入此字段。
     */
    private Merchant    merchant;
    /**
     * A brief description of the order
     * 订单的简要描述
     */
    private String      orderDescription;

    public String getReferenceOrderId() {
        return referenceOrderId;
    }

    public void setReferenceOrderId(String referenceOrderId) {
        this.referenceOrderId = referenceOrderId;
    }

    public Amount getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Amount orderAmount) {
        this.orderAmount = orderAmount;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
}