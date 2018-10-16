package com.github.jccode.esdemo;

import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

/**
 * LowLevelApiDemo
 *
 * @author 01372461
 */
@Component
public class LowLevelApiDemo {

    private static final String INDEX = "rate_class_index";
    private static final String TYPE = "rate_class_type";
    private static final String URL = INDEX+"/"+TYPE;

    @Autowired
    private RestClient client;

    public void demo() throws IOException {
        getMapping();
        getDoc();
        search();
    }

    private void getMapping() throws IOException {
        Response res = client.performRequest("GET", URL + "/_mapping");
        String s = EntityUtils.toString(res.getEntity());
        System.out.println(s);
    }

    private void getDoc() throws IOException {
        String id = "100";
        Response res = client.performRequest("GET", URL + String.format("/%s?pretty", id));
        String s = EntityUtils.toString(res.getEntity());
        System.out.println(s);
    }

    private void search() throws IOException {
        String q = "{\n" +
                "  \"query\": {\"match_all\": {}}\n" +
                "}";
        NStringEntity entity = new NStringEntity(q, ContentType.APPLICATION_JSON);
        Response res = client.performRequest("GET", URL + "/_search?pretty", Collections.emptyMap(), entity);

        String s = EntityUtils.toString(res.getEntity());
        System.out.println(s);
    }
}
