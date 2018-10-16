package com.github.jccode.esdemo.it;

import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import java.io.IOException;

/**
 * RateSearchIT
 *
 * @author 01372461
 */
//@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE)
//@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
//@RunWith(com.carrotsearch.randomizedtesting.RandomizedRunner.class)
public class RateSearchESTest extends ESIntegTestCase {

    private final static String INDEX = "rate_class_index";
    private final static String TYPE = "rate_class_type";
    private static final String URL = INDEX+"/"+TYPE;

    @Test
    public void testSimpleMatchAll() throws IOException {
        System.out.println("Hello");

        Response res = getRestClient().performRequest("GET", URL + "/_mapping?pretty");
        String s = EntityUtils.toString(res.getEntity());
        System.out.println(s);
        System.out.println("------------------");

        assertTrue(true);
    }
}
