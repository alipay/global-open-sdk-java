package com.alipay.global.api.model.ams;

public class Shipping {

    private UserName shippingName;
    private Address shippingAddress;
    private String   shippingCarrier;
    private String   shippingPhoneNo;

    private String   shipToEmail;

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

    public String getShipToEmail() {
        return shipToEmail;
    }

    public void setShipToEmail(String shipToEmail) {
        this.shipToEmail = shipToEmail;
    }
}
