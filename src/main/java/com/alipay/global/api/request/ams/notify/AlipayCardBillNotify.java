package com.alipay.global.api.request.ams.notify;

import com.alipay.global.api.model.ams.Amount;
import com.alipay.global.api.model.ams.FailReason;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlipayCardBillNotify{
    private String assetId;
    private String maskedCardNo;
    private String orderNo;
    private String cardNickName;
    private String transactionTime;
    private String merchantName;
    private Amount tradeAmount;
    private Amount inAmount;
    private Amount outAmount;
    private String exchangeRate;
    private String billType;
    private String tradeCountry;
    private String billStatus;
    private FailReason billFailReason;
    private String lastUpdate;
    private Map<String, String> metadata;

}
