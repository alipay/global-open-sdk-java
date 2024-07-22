package com.alipay.global.api.request.ams.marketPlace;

import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketPlace.AlipaySubmitAttachmentResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipaySubmitAttachmentRequest extends AlipayRequest<AlipaySubmitAttachmentResponse> {


    private String        submitAttachmentRequestId;
    private String        attachmentType;
    private String        fileSha256;


    public AlipaySubmitAttachmentRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_SUBMITATTACHMENT_PATH);
    }


    @Override
    public Class<AlipaySubmitAttachmentResponse> getResponseClass() {
        return AlipaySubmitAttachmentResponse.class;
    }
}
