package com.github.jccode.springbootrestdemo.common.data;

public class Success<T> extends RestResult {

    public Success() {
        super(false);
    }

    public Success(T payload) {
        super(false, payload);
    }
}
