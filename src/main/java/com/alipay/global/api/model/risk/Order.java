/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.model.risk;

import com.alipay.global.api.model.ams.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  /**
   * A unique ID on the merchant's side that identifies the order and is assigned by the merchant
   * who provides the service or product directly to the customer. 商户侧识别订单的唯一ID，由直接向买家提供服务或商品的商户分配。
   */
  private String referenceOrderId;
  /** The amount of the merchant's order 商户侧订单金额 */
  private Amount orderAmount;
  /**
   * Product information, including the ID, name, price, quantity, etc. of the items in the order.
   * 商品信息，包括订单中商品的ID、名称、价格、数量等。
   */
  private List<Goods> goods;
  /**
   * Shipping information, including consignee's information (name, phone number, email, and
   * shipping address) and delivery service provider information.
   * 送货信息，包括收货人的信息（姓名、电话号码、电子邮件和送货地址）和送货服务提供商的信息。
   */
  private Shipping shipping;
  /**
   * Secondary business information, if you are an acquirer with a second-tier merchant, pass this
   * field. 二级商户信息，如果您是拥有二级商户的收单机构，请传入此字段。
   */
  private Merchant merchant;
  /** A brief description of the order 订单的简要描述 */
  private String orderDescription;

  /**
   * Trip information, including trip modes, legs of trips and passenger information.
   * 行程信息，包括行程模式、行程段和乘客信息。
   */
  private Transit transit;

  /**
   * Information about lodging service that was purchased, including hotel name, hotel address,
   * check-in date, check-out date, number of booked rooms, number of booked nights and guest names.
   * 酒店信息，包括酒店名称、酒店地址、入住日期、离店日期、预订房间数、预订天数和入住人姓名。
   */
  private Lodging lodging;

  /**
   * Information about gaming top-up, including game name, the topped up username or ID, user email
   * and user phone number. 游戏充值信息，包括游戏名称、充值用户姓名或ID、用户邮箱和用户电话号码。
   */
  private Gaming gaming;

  /** The time when the order is created. 订单创建时间 */
  private Date orderCreatedTime;
}
