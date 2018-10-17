package com.github.jccode.esdemo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Json工具类
 * 
 * @author chenl
 * 
 */
public final class JsonUtils {

	private final static ObjectMapper mapper = new ObjectMapper();

	private JsonUtils() {
	}

	public static Map<String, Object> toMap(String json) throws IOException {
		return mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
	}
}
