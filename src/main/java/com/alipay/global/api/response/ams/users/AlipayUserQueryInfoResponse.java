package com.alipay.global.api.response.ams.users;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayUserQueryInfoResponse extends AlipayResponse {

    private String userId;
    private String userLoginId;
    private String hashUserLoginId;

}
