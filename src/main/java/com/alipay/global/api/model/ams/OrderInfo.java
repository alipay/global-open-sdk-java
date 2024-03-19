package com.alipay.global.api.model.ams;

/**
 * @Author yanhong
 * @version $Id: OrderInfo.java, v 0.1 2024年03月19日 3:47 PM yanhong Exp $
 **/
public class OrderInfo {

    private Amount orderAmount;

    public Amount getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Amount orderAmount) {
        this.orderAmount = orderAmount;
    }
}
