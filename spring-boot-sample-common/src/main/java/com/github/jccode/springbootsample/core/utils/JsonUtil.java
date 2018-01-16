package com.github.jccode.springbootsample.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


/**
 * Json util
 *
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JsonUtil() {
    }

    public static ObjectMapper getMapper() {
        return objectMapper;
    }

    public static <T> String toJson(T t) {
        try {
            if (t == null) return null;
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <T> T fromJson(String s, Class<T> clazz) {
        try {
            return objectMapper.readValue(s, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <T> T fromJson(String s, TypeReference type) {
        try {
            return objectMapper.readValue(s, type);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
