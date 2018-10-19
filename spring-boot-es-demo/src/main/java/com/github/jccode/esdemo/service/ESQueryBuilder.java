package com.github.jccode.esdemo.service;

import com.github.jccode.esdemo.dto.RateCalcQueryDto;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WrapperQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * ESQueryBuilder
 *
 * @author 01372461
 */
public class ESQueryBuilder {

    public static SearchSourceBuilder ratePriorityQuery(RateCalcQueryDto query) {
        String q = "{\n" +
                "    \"bool\": {\n" +
                "      \"must\": [\n" +
                "        {\"term\": {\"isEffective\": \"1\"}},\n" +
                "        {\"nested\": {\n" +
                "          \"path\": \"items\",\n" +
                "          \"score_mode\": \"max\",\n" +
                "          \"inner_hits\": {\"size\": 10},\n" +
                "          \"query\": {\n" +
                "            \"bool\": {\n" +
                "              \"filter\": [\n" +
                "                {\"match\": {\"items.suitTimeList\": \"%s\"}},\n" +
                "                {\"match\": {\"items.originAirportList\": \"%s\"}},\n" +
                "                {\"match\": {\"items.destAirportList\": \"%s\"}}\n" +
                "              ],\n" +
                "              \"should\": [\n" +
                "                {\"function_score\": {\n" +
                "                  \"functions\": [\n" +
                "                    {\n" +
                "                      \"filter\": {\"term\": {\"items.transfersPointList\": \"%s\"}},\n" +
                "                      \"weight\": 3\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"filter\": {\"term\": {\"items.suitFlightList\": \"%s\"}},\n" +
                "                      \"weight\": 2\n" +
                "                    },\n" +
                "                    {\n" +
                "                      \"filter\": {\"term\": {\"items.suitFlightList\": \"ALL\"}},\n" +
                "                      \"weight\": 1\n" +
                "                    }\n" +
                "                  ],\n" +
                "                  \"score_mode\": \"sum\"\n" +
                "                }}\n" +
                "              ]\n" +
                "            }\n" +
                "          }\n" +
                "        }}\n" +
                "      ],\n" +
                "      \"should\": [\n" +
                "        {\"function_score\": {\n" +
                "          \"functions\": [\n" +
                "            {\n" +
                "              \"filter\": {\"term\": {\"suitAgtList\": \"%s\"}},\n" +
                "              \"weight\": 6\n" +
                "            }\n" +
                "          ],\n" +
                "          \"score_mode\": \"sum\"\n" +
                "        }}\n" +
                "      ]\n" +
                "    }\n" +
                "  }";

        String q2 = String.format(q, query.getDate(), query.getOriginAirportList(), query.getDestAirportList(), query.getTransfersPointList(), query.getSuitFlightList(), query.getSuitAgtList());
        WrapperQueryBuilder queryBuilder = QueryBuilders.wrapperQuery(q2);
        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder);
        sourceBuilder.fetchSource("*", "items");
        return sourceBuilder;
    }
}
