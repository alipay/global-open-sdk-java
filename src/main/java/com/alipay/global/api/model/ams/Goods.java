package com.alipay.global.api.model.ams;

public class Goods {
    private String referenceGoodsId;
    private String goodsName;
    private String goodsCategory;
    private String goodsBrand;
    private Amount goodsUnitAmount;
    private String goodsQuantity;
    private String goodsSkuName;

    public String getReferenceGoodsId() {
        return referenceGoodsId;
    }

    public void setReferenceGoodsId(String referenceGoodsId) {
        this.referenceGoodsId = referenceGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public Amount getGoodsUnitAmount() {
        return goodsUnitAmount;
    }

    public void setGoodsUnitAmount(Amount goodsUnitAmount) {
        this.goodsUnitAmount = goodsUnitAmount;
    }

    public String getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(String goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public String getGoodsSkuName() {
        return goodsSkuName;
    }

    public void setGoodsSkuName(String goodsSkuName) {
        this.goodsSkuName = goodsSkuName;
    }
}
