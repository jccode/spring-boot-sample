package com.github.jccode.esdemo;

import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * HighLevelApiDemo
 *
 * @author 01372461
 */
@Component
public class HighLevelApiDemo {

    @Autowired
    private RestHighLevelClient client;

    public void demo() throws IOException {
        String index = "rate_class_index";
        String type = "rate_class_type";
        boolean exists = indicesExists(index);
        System.out.println(index + " is " + (exists ? "" : "not ") + "exist");
        if (exists) {
            get(index, type, "100");
            search1(index, type);
            search2(index, type);
        }

        close();
    }

    private void close() throws IOException {
        client.close();
    }

    private boolean indicesExists(String index) throws IOException {
        GetIndexRequest req = new GetIndexRequest();
        req.indices(index);
        boolean exists = client.indices().exists(req);
        return exists;
    }

    private void get(String index, String type, String id) throws IOException {
        GetRequest req = new GetRequest(index, type, id);
        GetResponse res = client.get(req);
        System.out.println(res);
    }

    private void search1(String index, String type) throws IOException {
        SearchRequest req = new SearchRequest(index);
        req.types(type);
        req.source(SearchSourceBuilder.searchSource().query(QueryBuilders.matchAllQuery()));
        SearchResponse res = client.search(req);
        System.out.println(res);
    }

    private void search2(String index, String type) throws IOException {
        SearchRequest req = new SearchRequest(index);
        req.types(type);

        //String q = "{\"match_all\": {}}\n";
        String q = "{\n" +
                "    \"bool\": {\n" +
                "      \"filter\": [\n" +
                "        {\"term\": {\"isEffective\":\"1\"}},\n" +
                "        {\"range\": {\"effectiveStartDate\": {\"lte\": \"20180928\"}}},\n" +
                "        {\"range\": {\"effectiveEndDate\": {\"gte\": \"20180928\"}}}\n" +
                "      ],\n" +
                "      \"should\": [\n" +
                "        {\"match\": {\"suitAgtList\": \"A001\"}}\n" +
                "      ]\n" +
                "    }\n" +
                "  }";
        WrapperQueryBuilder builder = QueryBuilders.wrapperQuery(q);

        req.source(SearchSourceBuilder.searchSource().query(builder));

        SearchResponse res = client.search(req);
        System.out.println(res);
    }
}
