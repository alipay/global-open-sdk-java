package com.alipay.global.api.request.ams.billing;

import com.alipay.global.api.request.AlipayFileRequest;
import com.alipay.global.api.response.ams.billing.AlipayProductUploadImageResponse;
import java.io.File;
import java.io.InputStream;

/** Request for uploading an image that can later be associated with a product. */
public final class AlipayProductUploadImageRequest
    extends AlipayFileRequest<AlipayProductUploadImageResponse> {

  private String productId;

  public AlipayProductUploadImageRequest() {}

  public AlipayProductUploadImageRequest(String productId, File file) {
    this();
    this.productId = productId;
    setFile(file);
  }

  public AlipayProductUploadImageRequest(
      String productId, InputStream inputStream, String fileName) {
    this();
    this.productId = productId;
    setFile(inputStream, fileName);
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }
}
