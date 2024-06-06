package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskData {
    /**
     * The order information used for risk control purposes.
     */
    private RiskOrder order;

    /**
     * The buyer information used for risk control purposes.
     */
    private RiskBuyer buyer;

    /**
     * The environment information used for risk control purposes.
     */
    private RiskEnv env;

    /**
     * The information provided by a merchant to identify a risky transaction.
     */
    private RiskSignal riskSignal;

    /**
     * The address information used for risk control purposes.
     */
    private RiskAddress address;

    /**
     * The verification method that a merchant uses for a card payment.
     */
    private CardVerificationResult cardVerificationResult;

}