package com.alipay.global.api.request.ams.notify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlipayCardStatusNotify {
    private String requestId;      // 集成商指定的唯一请求ID（幂等性）
    private String assetId;        // 卡资产ID
    private String operateType;    // 操作类型：ADD / FREEZE / UNFREEZE / CANCEL
    private String cardStatus;     // 当前卡状态：ACTIVE / FROZEN / CANCEL
    private String createdTime;    // 创建时间（ISO 8601 UTC+时区）
    private String updatedTime;    // 更新时间（ISO 8601）
    private String cardBrand;      // 卡组织品牌（如 MASTERCARD），可选
}
