package com.github.jccode.springbootsample.core.web;


import com.github.jccode.springbootsample.core.data.rest.Failed;
import com.github.jccode.springbootsample.core.exception.RestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(RestException.class)
    public Failed restException(Exception e, HttpServletRequest request) {
        return new Failed(e.getMessage());
    }
}
