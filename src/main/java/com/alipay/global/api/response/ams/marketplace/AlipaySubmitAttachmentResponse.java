package com.alipay.global.api.response.ams.marketplace;

import com.alipay.global.api.model.ams.AttachmentType;
import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubmitAttachmentResponse extends AlipayResponse {

    private String submitAttachmentRequestId;
    private AttachmentType attachmentType;
    private String attachmentKey;

}
