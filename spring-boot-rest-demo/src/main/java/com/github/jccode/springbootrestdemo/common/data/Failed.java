package com.github.jccode.springbootrestdemo.common.data;

public class Failed extends RestResult<String> {

    public Failed() {
        super(true);
    }

    public Failed(String payload) {
        super(true, payload);
    }
}
