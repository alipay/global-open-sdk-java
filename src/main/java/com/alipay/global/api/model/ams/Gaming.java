package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gaming {

    private String gameName;
    private String toppedUpUser;
    private String toppedUpEmail;
    private String toppedUpPhoneNo;
}
