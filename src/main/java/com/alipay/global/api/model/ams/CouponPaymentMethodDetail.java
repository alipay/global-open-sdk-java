package com.alipay.global.api.model.ams;

public class CouponPaymentMethodDetail {

    private String  couponId;
    private Amount  availableAmount;
    private String  couponName;
    private String  couponDescription;
    private String  couponExpireTime;
    private String  paymentMethodDetailMetadata;

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

    public String getCouponExpireTime() {
        return couponExpireTime;
    }

    public void setCouponExpireTime(String couponExpireTime) {
        this.couponExpireTime = couponExpireTime;
    }

    public String getPaymentMethodDetailMetadata() {
        return paymentMethodDetailMetadata;
    }

    public void setPaymentMethodDetailMetadata(String paymentMethodDetailMetadata) {
        this.paymentMethodDetailMetadata = paymentMethodDetailMetadata;
    }

}
