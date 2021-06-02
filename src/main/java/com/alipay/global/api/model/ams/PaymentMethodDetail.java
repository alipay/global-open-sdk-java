package com.alipay.global.api.model.ams;

public class PaymentMethodDetail {


    private PaymentMethodDetailType     paymentMethodDetailType;
    private CardPaymentMethodDetail card;
    private ExternalPaymentMethodDetail externalAccount;
    private DiscountPaymentMethodDetail discount;
    private CouponPaymentMethodDetail coupon;
    private String                      extendInfo;

    public PaymentMethodDetailType getPaymentMethodDetailType() {
        return paymentMethodDetailType;
    }

    public void setPaymentMethodDetailType(PaymentMethodDetailType paymentMethodDetailType) {
        this.paymentMethodDetailType = paymentMethodDetailType;
    }

    public CardPaymentMethodDetail getCard() {
        return card;
    }

    public void setCard(CardPaymentMethodDetail card) {
        this.card = card;
    }

    public ExternalPaymentMethodDetail getExternalAccount() {
        return externalAccount;
    }

    public void setExternalAccount(ExternalPaymentMethodDetail externalAccount) {
        this.externalAccount = externalAccount;
    }

    public DiscountPaymentMethodDetail getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountPaymentMethodDetail discount) {
        this.discount = discount;
    }

    public CouponPaymentMethodDetail getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponPaymentMethodDetail coupon) {
        this.coupon = coupon;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }
}
