package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.Order;
import com.alipay.global.api.model.ams.PromotionResult;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayRetrievePaymentSessionResponse extends AlipayResponse {
    private Order order;
    private List<PromotionResult> promotionResults;
    private String customizedInfo;
}
