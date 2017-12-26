package com.github.jccode.springbootsample.core.data.rest;

public class Failed<T> extends RestResult<T> {

    public Failed() {
        super(true);
    }

    public Failed(T payload) {
        super(true, payload);
    }
}
