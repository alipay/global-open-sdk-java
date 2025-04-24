package com.alipay.global.api.request.ams.users;

import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.users.AlipayUserQueryInfoResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayUserQueryInfoRequest extends AlipayRequest<AlipayUserQueryInfoResponse> {

    private String accessToken;

    public AlipayUserQueryInfoRequest() {
        this.setPath("/ams/api/v1/users/inquiryUserInfo");
    }

    @Override
    public Class<AlipayUserQueryInfoResponse> getResponseClass() {
        return AlipayUserQueryInfoResponse.class;
    }

}
