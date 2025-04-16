package com.alipay.global.api.response.ams.subscription;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubscriptionCreateResponse extends AlipayResponse {

    private String schemeUrl;

    private String applinkUrl;

    private String normalUrl;

    private String appIdentifier;

}
