package com.github.jccode.springbootsample.core.utils;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springbootsample.core.exception.RestException;

import java.io.Serializable;

public class CommonUtil {

    public static void fault(String message) {
        throw new RestException(message);
    }

    public static <T> RestResult<T> success(T t) {
        return RestResult.success(t);
    }

    public static <T> RestResult<T> fail(String msg, Object r) {
        return RestResult.fail(msg, r);
    }

    public static <T> RestResult<T> fail(String msg) {
        return RestResult.fail(msg);
    }

    public static <T> RestResult<T> restResult(T t, String msg) {
        return RestResult.create(t, msg);
    }

    public static <T> RestResult<T> restResult(T t) {
        return RestResult.create(t);
    }
}
