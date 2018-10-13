package com.boomboo.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static Map<String, String> toMap(Object o) {
        Map<String, String> props = mapper.convertValue(o, Map.class);
        return props;
    }

    public static Map<String, Object> toMapObject(Object o) {
        Map<String, Object> props = mapper.convertValue(o, Map.class);
        return props;
    }

    public static JSONObject toJSONObject(Object obj) {
        return mapper.convertValue(obj, JSONObject.class);
    }

    public static String toString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }

    public static Object parse(String s, Class c) throws IOException {
        return mapper.readValue(s, c);
    }

    public static Object parse(JsonNode s, Class c) throws IOException {
        return mapper.treeToValue(s, c);
    }

    public static JsonNode readAsTree(String jsonStr) throws IOException {
        JsonNode jsonNode = mapper.readTree(jsonStr);
        return jsonNode;
    }

    public static String toStringV2(Object o) {
        String result = null;
        try {
            result = mapper.writeValueAsString(o);
        } catch (JsonProcessingException jpe) {
            logger.error("convert object to json error: {}", jpe);
        }
        return result;
    }

    public static <T> T convertValue(Object source, Class<T> targetClass) {
        return mapper.convertValue(source, targetClass);
    }

    public static <T> List<T> convertListValue(String source, Class<T> targetClass) throws IOException {
        JavaType type = mapper.getTypeFactory().
                constructCollectionType(List.class, targetClass);
        return mapper.readValue(source, type);
    }

    public static <T> T convertDuplicateValue(String source, Class<T> param1Class, Class<?> param2Class) throws IOException {
        JavaType type = mapper.getTypeFactory().constructParametrizedType(param1Class, param2Class, param2Class);
        return mapper.readValue(source, type);
    }

    public static <T> T convertDuplicateValue(String source, Class<T> param1Class, Class<?> param2Class, Class<?> param3Class) throws IOException {
        JavaType type = mapper.getTypeFactory().constructParametrizedType(param1Class, param2Class, param3Class);
        return mapper.readValue(source, type);
    }

}