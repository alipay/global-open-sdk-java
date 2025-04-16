package com.alipay.global.api.request.ams.marketplace;

import com.alipay.global.api.model.ams.AttachmentType;
import com.alipay.global.api.model.constants.AntomPathConstants;
import com.alipay.global.api.request.AlipayRequest;
import com.alipay.global.api.response.ams.marketplace.AlipaySubmitAttachmentResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlipaySubmitAttachmentRequest extends AlipayRequest<AlipaySubmitAttachmentResponse> {

    private String submitAttachmentRequestId;

    /**
     * The type of the attachment that you submit. Valid values are:
     * <p>
     * AUTHORIZER_SIGNATURE_CONFIRMATION_LETTER: indicates that the attachment is a document that someone signs on behalf of an individual or a company.
     * ASSOCIATION_ARTICLE: indicates that the attachment contains the rules and regulations of the company.
     * FINANCIAL_REPORT: indicates that the attachment is the company's financial report.
     * OWNERSHIP_STRUCTURE_PIC: indicates that the attachment is the company's ownership structure chart.
     * ADDRESS_PROOF: indicates that the attachment serves as proof of the company member's residential address.
     * UBO_PROVE: indicates that the attachment is the declaration of the company's UBO whose shareholding ratio is less than 25%.
     * ENTERPRISE_REGISTRATION: indicates the enterprise registration certificate.
     * LICENSE_INFO: indicates that the certificate is a business license.
     * ID_CARD: indicates that the certificate is an identity card.
     * PASSPORT: indicates that the certificate is a passport.
     * DRIVING_LICENSE: indicates that the certificate is a driving license.
     * CPF: indicates that the certificate is the Cadastro Pessoal de Pessoa Física (CPF) of the Brazilian individual taxpayer.
     * CNPJ: indicates that the certificate is the Cadastro Nacional da Pessoa Jurídica (CNPJ), which is a federal identification number assigned to companies and businesses operating in Brazil.
     */
    private AttachmentType attachmentType;
    private String fileSha256;

    public AlipaySubmitAttachmentRequest() {
        this.setPath(AntomPathConstants.MARKETPLACE_SUBMITATTACHMENT_PATH);
    }

    @Override
    public Class<AlipaySubmitAttachmentResponse> getResponseClass() {
        return AlipaySubmitAttachmentResponse.class;
    }
}
