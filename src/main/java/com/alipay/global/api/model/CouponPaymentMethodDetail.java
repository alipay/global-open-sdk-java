package com.alipay.global.api.model;

import java.util.Date;

public class CouponPaymentMethodDetail {

    private String  couponId;
    private Amount  availableAmount;
    private String  couponName;
    private String  couponDescription;
    private Date    couponExpireTime;
    private String  paymentMethodDetailMetadatal;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Amount getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Amount availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
    }

    public Date getCouponExpireTime() {
        return couponExpireTime;
    }

    public void setCouponExpireTime(Date couponExpireTime) {
        this.couponExpireTime = couponExpireTime;
    }

    public String getPaymentMethodDetailMetadatal() {
        return paymentMethodDetailMetadatal;
    }

    public void setPaymentMethodDetailMetadatal(String paymentMethodDetailMetadatal) {
        this.paymentMethodDetailMetadatal = paymentMethodDetailMetadatal;
    }

}
