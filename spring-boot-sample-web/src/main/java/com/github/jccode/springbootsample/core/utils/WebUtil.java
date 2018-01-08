package com.github.jccode.springbootsample.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jccode.springbootsample.core.exception.RestException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class WebUtil {

    public static void checkResult(BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            List<String> errors = fieldErrors.stream().map(e -> e.getField() + " " + e.getDefaultMessage()).collect(Collectors.toList());
            try {
                String s = new ObjectMapper().writeValueAsString(errors);
                throw new RestException(s);
            } catch (JsonProcessingException e) {
                throw new RestException(errors.toString());
            }
        }
    }
}
