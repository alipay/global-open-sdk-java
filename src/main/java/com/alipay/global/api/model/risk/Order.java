/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.Goods;
import com.alipay.global.api.model.ams.Shipping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /**
     * A unique ID on the merchant's side that identifies the order and is assigned by the merchant who provides the service or product
     * directly to the customer.
     * 商户侧识别订单的唯一ID，由直接向买家提供服务或商品的商户分配。
     */
    private String referenceOrderId;
    /**
     * The amount of the merchant's order
     * 商户侧订单金额
     */
    private Amount orderAmount;
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
    private Shipping shipping;
    /**
     * Secondary business information, if you are an acquirer with a second-tier merchant, pass this field.
     * 二级商户信息，如果您是拥有二级商户的收单机构，请传入此字段。
     */
    private Merchant merchant;
    /**
     * A brief description of the order
     * 订单的简要描述
     */
    private String orderDescription;

    /**
     * The time when the order is created.
     * 订单创建时间
     */
    private Date orderCreatedTime;

}