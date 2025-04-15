package com.alipay.global.api.response.ams.pay;

import com.alipay.global.api.model.ams.Quote;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayInquireExchangeRateResponse extends AlipayPayResponse{
    private List<Quote> quotes;
}
