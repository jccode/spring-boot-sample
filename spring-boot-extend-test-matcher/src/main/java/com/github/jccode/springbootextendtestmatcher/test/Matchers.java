package com.github.jccode.springbootextendtestmatcher.test;

import org.hamcrest.Matcher;
import org.springframework.test.web.servlet.ResultMatcher;


public class Matchers {

    public static <T> ResultMatcher extractJsonPath(String expression, Matcher<T> matcher) {
        return new ExtractJsonPathResultMatchers(expression).value(matcher);
    }
}
