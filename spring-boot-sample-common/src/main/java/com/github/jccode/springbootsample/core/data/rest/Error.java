package com.github.jccode.springbootsample.core.data.rest;

public class Error {

    private String code;

    private String message;

    private Object data;

    public Error() {
    }

    public Error(String message) {
        this.message = message;
    }

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Error(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Error(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
