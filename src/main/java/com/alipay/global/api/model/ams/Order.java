package com.alipay.global.api.model.ams;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String referenceOrderId;
    private String orderDescription;
    private Amount orderAmount;
    private Merchant merchant;
    private List<Goods> goods;
    private Shipping shipping;
    private Buyer buyer;
    private Env env;
    private String extendInfo;
    private Transit transit;
    private Lodging lodging;
    private Gaming gaming;
    private Boolean needDeclaration;

}
