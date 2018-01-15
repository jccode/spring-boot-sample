package com.github.jccode.springcloud.integrateddemo.common.event.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventHandler {

    @AliasFor("type")
    String value() default "";

    @AliasFor("value")
    String type() default "";

}
