package com.github.jccode.springbootsample.core.data.rest;

public class Success<T> extends RestResult {

    public Success() {
        super(false);
    }

    public Success(T payload) {
        super(false, payload);
    }
}
