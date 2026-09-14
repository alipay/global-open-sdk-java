package com.alipay.global.api;

import com.alipay.global.api.exception.AlipayApiException;
import com.alipay.global.api.request.AlipayFileRequest;
import com.alipay.global.api.request.ams.billing.AlipayProductUploadImageRequest;
import com.alipay.global.api.response.AlipayResponse;
import com.alipay.global.api.response.ams.billing.AlipayProductUploadImageResponse;
import com.alipay.global.api.tools.JsonUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/** Internal registry of the file upload APIs that the SDK explicitly supports. */
final class FileUploadOperationRegistry {

  private static final Map<Class<?>, FileUploadOperation<?>> OPERATIONS;

  static {
    Map<Class<?>, FileUploadOperation<?>> operations =
        new HashMap<Class<?>, FileUploadOperation<?>>();
    operations.put(
        AlipayProductUploadImageRequest.class,
        new FileUploadOperation<AlipayProductUploadImageResponse>(
            "/ams/api/v1/billing/product/uploadImage",
            2L * 1024L * 1024L,
            Arrays.asList("file", "imageFile"),
            AlipayProductUploadImageResponse.class,
            true,
            new ProductImageUploadAdapter()));
    OPERATIONS = Collections.unmodifiableMap(operations);
  }

  private FileUploadOperationRegistry() {}

  @SuppressWarnings("unchecked")
  static <T extends AlipayResponse> FileUploadOperation<T> resolve(AlipayFileRequest<T> request)
      throws AlipayApiException {
    FileUploadOperation<?> operation = OPERATIONS.get(request.getClass());
    if (operation == null) {
      throw new AlipayApiException("Only SDK-provided file upload requests are supported");
    }
    return (FileUploadOperation<T>) operation;
  }

  private static final class ProductImageUploadAdapter
      implements FileUploadOperation.RequestAdapter {

    @Override
    public void validate(AlipayFileRequest<?> request) throws AlipayApiException {
      AlipayProductUploadImageRequest productRequest = (AlipayProductUploadImageRequest) request;
      if (StringUtils.isBlank(productRequest.getProductId())) {
        throw new AlipayApiException("productId can't be blank");
      }
      if (productRequest.getProductId().length() > 64) {
        throw new AlipayApiException("productId length can't exceed 64 characters");
      }
    }

    @Override
    public String buildBody(AlipayFileRequest<?> request, String fileSha256)
        throws AlipayApiException {
      if (StringUtils.isBlank(fileSha256)) {
        throw new AlipayApiException("fileSha256 can't be blank");
      }
      AlipayProductUploadImageRequest productRequest = (AlipayProductUploadImageRequest) request;
      Map<String, String> body = new LinkedHashMap<String, String>();
      body.put("productId", productRequest.getProductId());
      body.put("fileSha256", fileSha256);
      return JsonUtil.toJson(body);
    }
  }
}
