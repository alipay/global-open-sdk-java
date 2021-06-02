package com.alipay.global.api.model.aps;

public class Shipping {

    private UserName shippingName;
    private Address  shippingAddress;
    private String   shippingCarrier;
    private String   shippingPhoneNo;

    public UserName getShippingName() {
        return shippingName;
    }

    public void setShippingName(UserName shippingName) {
        this.shippingName = shippingName;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingCarrier() {
        return shippingCarrier;
    }

    public void setShippingCarrier(String shippingCarrier) {
        this.shippingCarrier = shippingCarrier;
    }

    public String getShippingPhoneNo() {
        return shippingPhoneNo;
    }

    public void setShippingPhoneNo(String shippingPhoneNo) {
        this.shippingPhoneNo = shippingPhoneNo;
    }
}
