/*
 * Ant Group
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.global.api.request.ams.risk;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.risk.RiskReportResponse;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** The request of Ant Group's risk report API. 调用蚂蚁集团风险上报接口的请求参数。 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RiskReportRequest extends AlipayRequest<RiskReportResponse> {
  /**
   * A unique ID assigned to a merchant who provides a service or product directly to a customer and
   * is used to identify the transaction. 直接向买家提供服务或商品的商户分配的唯一ID，用于识别交易。
   */
  private String referenceTransactionId;
  /**
   * The reason for the report.Providing this information can help improve the accuracy of fraud
   * detection, and increase payment success rates. 上报的原因,提供这些信息有助于提升欺诈检测的准确性，提高支付成功率。
   */
  private String reportReason;
  /**
   * The type of risk reported. Valid values are as follows: SUSPICIOUS: indicates that the
   * transaction is considered risky by the merchant, such as the buyer hit the merchant's
   * blacklist. CHARGEBACK: indicates that the buyer initiated a chargeback. FRAUD: indicates that
   * fraudulent card fraud has occurred. 上报的风险类型。有效值为： SUSPICIOUS：表示该交易被商户视为有风险，例如买家命中了商户的黑名单。
   * CHARGEBACK：表示买家发起了拒付。 FRAUD：表示发生了卡盗刷行为。
   */
  private String riskType;
  /**
   * Represents the time when a risk event occurs, defined as follows: If the riskType value is
   * SUSPICIOUS, the value of this field is the time when you identified the risky transaction. If
   * the value of riskType is CHARGEBACK, the value of this field is the time when the chargeback
   * occurred included in the notification that the payment method sent you. If the value of
   * riskType is FRAUD, the value of this field is the time of the card theft that is included in
   * the notification sent to you by the payment method. 表示风险事件发生的时间，定义如下： 如果 riskType 值为
   * SUSPICIOUS， 该字段的值为您识别该风险交易的时间。 如果 riskType 的值为 CHARGEBACK，该字段的值为支付方式向您发送的通知中包含的拒付发生时间。 如果
   * riskType 的值为 FRAUD，该字段的值为支付方式向您发送的通知中包含的盗卡发生时间。
   */
  private Date riskOccurrenceTime;

  public RiskReportRequest() {
    this.setPath(AntomPathConstants.RISK_REPORT_PATH);
  }

  @Override
  public boolean usingSandboxUrl() {
    return false;
  }

  @Override
  public Class<RiskReportResponse> getResponseClass() {
    return RiskReportResponse.class;
  }
}
