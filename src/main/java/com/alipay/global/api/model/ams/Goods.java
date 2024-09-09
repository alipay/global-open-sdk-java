package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    private String referenceGoodsId;
    private String goodsName;
    private String goodsCategory;
    private String goodsBrand;
    private Amount goodsUnitAmount;
    private String goodsQuantity;
    private String goodsSkuName;
    private String goodsUrl;
    private String deliveryMethodType;

    private String goodsImageUrl;
    private String priceId;

}
