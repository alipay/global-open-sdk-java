package com.alipay.global.api.response.ams.billing;

import com.alipay.global.api.response.AlipayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** Response returned by the product image upload API. */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayProductUploadImageResponse extends AlipayResponse {

  /** The HTTPS URL generated for the uploaded image. Maximum length: 2048 characters. */
  private String imageUrl;

  /** The uploaded image file name. Maximum length: 128 characters. */
  private String imageName;

  /** The product ID echoed from the request. Maximum length: 64 characters. */
  private String productId;
}
