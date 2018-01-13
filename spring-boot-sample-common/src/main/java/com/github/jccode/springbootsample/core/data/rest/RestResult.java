package com.github.jccode.springbootsample.core.data.rest;

import java.util.function.Consumer;
import java.util.function.Function;

public class RestResult<R> {

    private boolean isError;

    private R payload;

    private Error error;

    public RestResult(boolean isError, R payload) {
        this.isError = isError;
        this.payload = payload;
    }

    public RestResult(boolean isError, Error error) {
        this.isError = isError;
        this.error = error;
    }

    public RestResult() {
    }

    public static <R> RestResult<R> success(R payload) {
        return new RestResult<>(false, payload);
    }

    public static <R> RestResult<R> fail(String message) {
        return new RestResult<>(true, new Error(message));
    }

    public static <R> RestResult<R> fail(String message, Object data) {
        return new RestResult<>(true, new Error(message, data));
    }

    public static <R> RestResult<R> create(R payload, String message) {
        return payload == null ? fail(message) : success(payload);
    }

    public static <R> RestResult<R> create(R payload) {
        return payload == null ? fail("payload is null") : success(payload);
    }

    public boolean isError() {
        return isError;
    }

    public boolean getIsError() {
        return isError;
    }

    public void setIsError(boolean error) {
        isError = error;
    }

    public R getPayload() {
        return payload;
    }

    public void setPayload(R payload) {
        this.payload = payload;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public <T> T map(Function<? super R, ? extends T> successFun) {
        return isError() ? null : successFun.apply(payload);
    }

    public void apply(Consumer<? super R> successFun) {
        successFun.accept(payload);
    }

    public <T> T map(
            Function<? super R, ? extends T> successFun,
            Function<? super Error, ? extends T> failFun
    ) {
        return isError() ?
                failFun.apply(error) :
                successFun.apply(payload);
    }

    public void apply(
            Consumer<? super R> successFun,
            Consumer<? super Error> failFun
    ) {
        if (isError()) {
            failFun.accept(error);
        } else {
            successFun.accept(payload);
        }
    }
}
