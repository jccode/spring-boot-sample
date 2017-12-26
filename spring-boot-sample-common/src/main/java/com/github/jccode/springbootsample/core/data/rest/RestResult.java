package com.github.jccode.springbootsample.core.data.rest;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest result
 *
 * @param <T>
 */
public class RestResult<T> {

    protected boolean error;

    protected T payload;

    protected Map<String, String> meta;

    public RestResult() {
    }

    public RestResult(boolean error) {
        this.error = error;
    }

    public RestResult(T payload) {
        this.payload = payload;
    }

    public RestResult(boolean error, T payload) {
        this.error = error;
        this.payload = payload;
    }

    public RestResult(boolean error, T payload, Map<String, String> meta) {
        this.error = error;
        this.payload = payload;
        this.meta = meta;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public void addMeta(String key, String value) {
        if (meta == null) meta = new HashMap<>();
        meta.put(key, value);
    }
}
