package com.github.jccode.springbootsample.core.data.rest;

public class Failed extends RestResult<String> {

    public Failed() {
        super(true);
    }

    public Failed(String payload) {
        super(true, payload);
    }
}
