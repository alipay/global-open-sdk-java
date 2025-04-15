package com.alipay.global.api.response.ams.vaults;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayUpdateTokenResponse extends AlipayResponse {
    private String token;
}
