package org.example;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;

/** 提供简单json序列化的静态方法 */
public class JacksonUtils {

  private static final JsonMapper jsonMapper;
  private static final JsonMapper snakeJsonMapper;
  private static final JsonMapper maskedMapper;

  static {
    jsonMapper =
        JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .build();
    snakeJsonMapper =
        JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .build();
    maskedMapper =
        JsonMapper.builder()
            .serializationInclusion(Include.NON_NULL)
            .build();
  }

  @SneakyThrows
  public static <T> T deserialize(byte[] jsonBytes, Class<T> type) {
    return jsonMapper.readerFor(type).readValue(jsonBytes);
  }

  @SneakyThrows
  public static <T> T deserialize(String jsonString, Class<T> type) {
    return jsonMapper.readerFor(type).readValue(jsonString);
  }

  public static ObjectMapper mapper() {
    return jsonMapper;
  }

  public static <T> T deserializeWithoutException(String jsonString, Class<T> type) {
    try {
      return jsonMapper.readerFor(type).readValue(jsonString);
    } catch (Exception e) {
      return null;
    }
  }

  @SneakyThrows
  public static <T> T deserialize(String jsonString, TypeReference<T> typeReference) {
    return jsonMapper.readerFor(typeReference).readValue(jsonString);
  }

  @SneakyThrows
  public static <T> T deserialize(JsonNode json, Class<T> type) {
    return jsonMapper.readerFor(type).readValue(json);
  }

  @SneakyThrows
  public static JsonNode parse(String jsonString) {
    return jsonMapper.readTree(jsonString);
  }

  public static JsonNode parseFrom(Object object) {
    if (object instanceof JsonNode) {
      return (JsonNode) object;
    }
    return parse(serialize(object));
  }

  @SneakyThrows
  public static byte[] serializeByte(Object object) {
    return jsonMapper.writeValueAsBytes(object);
  }

  @SneakyThrows
  public static String serialize(Object object) {
    return jsonMapper.writeValueAsString(object);
  }

  /** 将对象信息脱敏并序列化为json */
  public static String serializeMasked(Object object) {
    try {
      return maskedMapper.writeValueAsString(object);
    } catch (Exception e) {
      // 报错时显示错误信息
      return String.format("{\"serializeMaskedException\":\"%s\"}", e.getMessage() + object);
    }
  }

  @SneakyThrows
  public static <T> T deserializeSnake(String jsonString, Class<T> type) {
    return snakeJsonMapper.readerFor(type).readValue(jsonString);
  }

  @SneakyThrows
  public static <T> T deserializeSnake(String jsonString, TypeReference<T> typeReference) {
    return snakeJsonMapper.readerFor(typeReference).readValue(jsonString);
  }

  @SneakyThrows
  public static JsonNode parseSnake(String jsonString) {
    return snakeJsonMapper.readTree(jsonString);
  }

  @SneakyThrows
  public static String serializeSnake(Object object) {
    return snakeJsonMapper.writeValueAsString(object);
  }
}
