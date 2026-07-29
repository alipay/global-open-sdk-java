package com.alipay.global.api.tools;

import com.alipay.global.api.exception.AlipayApiException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * SDK 统一 JSON 序列化/反序列化工具（Jackson）。
 *
 * <p>此处集中锁定与历史 fastjson 引擎一致的行为，保证商户升级后收发报文不变：
 *
 * <ul>
 *   <li>NON_NULL：省略 null 字段（对齐 fastjson 默认，请求体不含 null）
 *   <li>FAIL_ON_UNKNOWN_PROPERTIES=false：容忍服务端新增的未知字段
 *   <li>READ_UNKNOWN_ENUM_VALUES_AS_NULL=true：未知枚举值解析为 null（对齐 fastjson 宽容，不抛异常）
 *   <li>WRITE_DATES_AS_TIMESTAMPS=true：Date 序列化为毫秒时间戳（对齐 fastjson 默认）
 * </ul>
 */
public final class JsonUtil {

  private static final ObjectMapper MAPPER = createMapper();

  private JsonUtil() {}

  private static ObjectMapper createMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    return mapper;
  }

  /** 序列化对象为 JSON 字符串。 */
  public static String toJson(Object value) throws AlipayApiException {
    try {
      return MAPPER.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new AlipayApiException("JSON serialize error", e);
    }
  }

  /** 反序列化 JSON 字符串为指定类型。 */
  public static <T> T parse(String content, Class<T> clazz) throws AlipayApiException {
    try {
      return MAPPER.readValue(content, clazz);
    } catch (JsonProcessingException e) {
      throw new AlipayApiException("JSON parse error", e);
    }
  }
}
