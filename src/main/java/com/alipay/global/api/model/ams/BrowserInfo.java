package com.alipay.global.api.model.ams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrowserInfo {

    /**
     * The accept header of the user's browser
     */
    private String acceptHeader;

    /**
     * Indicates whether the user's browser is able to run Java
     */
    private Boolean javaEnabled;

    /**
     * Indicates whether the user's browser is able to run Java
     */
    private Boolean javaScriptEnabled;

    /**
     * The language of the user's browser
     */
    private String language;

    /**
     * The user agent of the user's browser
     */
    private String userAgent;

}
