package com.alipay.global.api.request.ams.pay;


import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.pay.AlipayUploadInvoiceShippingFileResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayUploadInvoiceShippingFileRequest extends AlipayRequest<AlipayUploadInvoiceShippingFileResponse> {


    private String paymentRequestId;
    private String fileId;
    private String uploadFile;
    private String fileType;
    private String fileName;

    public AlipayUploadInvoiceShippingFileRequest() {
        this.setPath("/ams/api/v1/payments/uploadInvoiceShippingFile");
    }

    @Override
    public Class<AlipayUploadInvoiceShippingFileResponse> getResponseClass() {
        return AlipayUploadInvoiceShippingFileResponse.class;
    }
}
