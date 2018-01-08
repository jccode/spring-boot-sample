package com.github.jccode.springbootsample.core.utils;

import com.github.jccode.springbootsample.core.data.rest.Failed;
import com.github.jccode.springbootsample.core.data.rest.Success;
import com.github.jccode.springbootsample.core.exception.RestException;

public class CommonUtil {

    public static void fault(String message) {
        throw new RestException(message);
    }

    public static <T> Success<T> success(T t) {
        return new Success<>(t);
    }

    public static <T> Failed<T> fail(T t) {
        return new Failed<>(t);
    }

    public static Failed<String> fail(String msg) {
        return new Failed<>(msg);
    }
}
