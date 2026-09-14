package com.alipay.global.api.tools;

import com.alipay.global.api.exception.AlipayApiException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/** The SDK's JSON wire format, independent of application ObjectMapper configuration. */
public final class JsonUtil {
  private static final ObjectMapper MAPPER = createMapper();

  private JsonUtil() {}

  private static ObjectMapper createMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return mapper;
  }

  /**
   * Serializes a value using the SDK's API field and enum mappings.
   *
   * @param value the value to serialize
   * @return the JSON body; a null value produces JSON null
   * @throws AlipayApiException when the value cannot be serialized
   */
  public static String toJson(Object value) throws AlipayApiException {
    try {
      return MAPPER.writeValueAsString(value);
    } catch (JsonProcessingException | IllegalArgumentException e) {
      throw new AlipayApiException("Failed to serialize JSON.", e);
    }
  }

  /**
   * Parses JSON into a caller-selected type. Unknown properties are ignored.
   *
   * @param json the JSON body
   * @param type the expected Java type
   * @param <T> the expected result type
   * @return the parsed value, or null for a null or empty body
   * @throws AlipayApiException when JSON cannot be bound to the expected type
   */
  public static <T> T fromJson(String json, Class<T> type) throws AlipayApiException {
    if (json == null || json.trim().isEmpty()) {
      return null;
    }
    try {
      return MAPPER.readValue(json, type);
    } catch (JsonProcessingException | IllegalArgumentException e) {
      throw new AlipayApiException("Failed to parse JSON.", e);
    }
  }
}
