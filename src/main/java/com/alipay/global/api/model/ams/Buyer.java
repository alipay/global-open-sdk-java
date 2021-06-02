package com.alipay.global.api.model.ams;

public class Buyer {

    private String     referenceBuyerId;
    private UserName   buyerName;
    private String     buyerPhoneNo;
    private String     buyerEmail;

    public String getReferenceBuyerId() {
        return referenceBuyerId;
    }

    public void setReferenceBuyerId(String referenceBuyerId) {
        this.referenceBuyerId = referenceBuyerId;
    }

    public UserName getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(UserName buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhoneNo() {
        return buyerPhoneNo;
    }

    public void setBuyerPhoneNo(String buyerPhoneNo) {
        this.buyerPhoneNo = buyerPhoneNo;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }
}
