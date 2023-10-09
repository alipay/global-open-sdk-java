package com.alipay.global.api.model.ams;

public class RiskBuyer {
    /**
     * The buyer's note to a merchant.
     */
    private String noteToMerchant;

    /**
     * The buyer's note to a deliveryman or a take-out rider.
     */
    private String noteToShipping;

    /**
     * The successful orders the buyer made within the last one hour.
     */
    private Integer orderCountIn1H;

    /**
     * The successful orders the buyer made within the last 24 hour.
     */
    private Integer orderCountIn24H;

    public String getNoteToMerchant() {
        return noteToMerchant;
    }

    public void setNoteToMerchant(String noteToMerchant) {
        this.noteToMerchant = noteToMerchant;
    }

    public String getNoteToShipping() {
        return noteToShipping;
    }

    public void setNoteToShipping(String noteToShipping) {
        this.noteToShipping = noteToShipping;
    }

    public Integer getOrderCountIn1H() {
        return orderCountIn1H;
    }

    public void setOrderCountIn1H(Integer orderCountIn1H) {
        this.orderCountIn1H = orderCountIn1H;
    }

    public Integer getOrderCountIn24H() {
        return orderCountIn24H;
    }

    public void setOrderCountIn24H(Integer orderCountIn24H) {
        this.orderCountIn24H = orderCountIn24H;
    }
}