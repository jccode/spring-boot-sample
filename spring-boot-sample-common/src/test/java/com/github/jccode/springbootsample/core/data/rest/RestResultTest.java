package com.github.jccode.springbootsample.core.data.rest;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


public class RestResultTest {

    @Test
    public void restResultApiTest() {
        RestResult<Integer> ret1 = RestResult.success(10);
        assertThat(ret1.getPayload(), is(10));
        assertThat(ret1.isError(), is(false));
        assertThat(ret1.getError(), is(nullValue()));

        String errMsg = "error message";
        RestResult<Object> fail = RestResult.fail(errMsg);
        assertThat(fail.getPayload(), is(nullValue()));
        assertThat(fail.isError(), is(true));
        assertThat(fail.getError().getMessage(), is(errMsg));
    }

    @Test
    public void restResultTransform() {
        List<String> list = Arrays.asList("1", "2", "3");

        RestResult<List<String>> succRet = RestResult.success(list);
        String ret2 = succRet.map(l -> String.join(",", l));
        assertThat(ret2, is(String.join(",", list)));

        RestResult<List<String>> failRet = RestResult.fail("error occure");
        String ret3 = failRet.map(l -> String.join(",", l));
        assertThat(ret3, is(nullValue()));
    }
}
