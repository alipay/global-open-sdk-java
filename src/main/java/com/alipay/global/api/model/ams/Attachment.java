package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {

    private AttachmentType   attachmentType;
    private String           file;
    private String           attachmentName;
    private String           fileKey;

}
