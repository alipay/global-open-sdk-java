package com.alipay.global.api.model.ams;

public class RiskAddress {
    /**
     * The type of the receiver's phone number
     */
    private String shippingPhoneType;

    /**
     * Indicates whether the billing state is the same as the shipping state
     */
    private Boolean isBillShipStateSame;

    /**
     * Indicates whether a previous billing state is the same as the shipping state
     */
    private Boolean isPreviousStateSame;

    /**
     * The distance in meters between the buyer's location and their shipping address.
     */
    private Integer locToShipDistance;

    /**
     * The minimum distance in meters between the buyer's previous shipping address and their billing address.
     */
    private Integer minPreviousShipToBillDistance;

    public String getShippingPhoneType() {
        return shippingPhoneType;
    }

    public void setShippingPhoneType(String shippingPhoneType) {
        this.shippingPhoneType = shippingPhoneType;
    }

    public Boolean getBillShipStateSame() {
        return isBillShipStateSame;
    }

    public void setBillShipStateSame(Boolean billShipStateSame) {
        isBillShipStateSame = billShipStateSame;
    }

    public Boolean getPreviousStateSame() {
        return isPreviousStateSame;
    }

    public void setPreviousStateSame(Boolean previousStateSame) {
        isPreviousStateSame = previousStateSame;
    }

    public Integer getLocToShipDistance() {
        return locToShipDistance;
    }

    public void setLocToShipDistance(Integer locToShipDistance) {
        this.locToShipDistance = locToShipDistance;
    }

    public Integer getMinPreviousShipToBillDistance() {
        return minPreviousShipToBillDistance;
    }

    public void setMinPreviousShipToBillDistance(Integer minPreviousShipToBillDistance) {
        this.minPreviousShipToBillDistance = minPreviousShipToBillDistance;
    }
}