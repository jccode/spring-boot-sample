package com.github.jccode.esdemo.it;

import com.github.jccode.esdemo.ESBaseCase;
import com.github.jccode.esdemo.util.JsonUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.elasticsearch.test.hamcrest.ElasticsearchAssertions.*;

/**
 * RateSearchESTest
 *
 * @author 01372461
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ESBasicTest extends ESBaseCase {

    /*

    BeforeClass:
      - Create index, type
      - Instance client

    Before:
      - Given some data, expected data logic is correct.
      - Given can be implement as a function.

    After:
      - Auto test data of last test case.

    AfterClass:
      - Close client
      - Delete index

    */

    final static String index = randomIndexName();
    final static String type = "rate_class_type";


    final static String mapping = "{\n" +
            "  \"settings\": {\n" +
            "    \"number_of_shards\": 3,\n" +
            "    \"number_of_replicas\": 2,\n" +
            "    \"analysis\": { \n" +
            "      \"analyzer\": {\n" +
            "        \"semicolon\": {\n" +
            "          \"type\": \"pattern\",\n" +
            "          \"pattern\": \";\",\n" +
            "          \"lowercase\": false\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "\n" +
            "  \"mappings\": {\n" +
            "    \n" +
            "    \"rate_class_type\": {\n" +
            "      \"dynamic\":false,\n" +
            "      \"dynamic_date_formats\":[\"yyyy-MM-dd hh:mm:ss\", \"yyyy-MM-dd\" ],\n" +
            "      \"properties\": {\n" +
            "        \"id\": {\n" +
            "          \"type\": \"integer\",\n" +
            "          \"index\": true,\n" +
            "          \"store\": true\n" +
            "        },\n" +
            "        \"rateDocNo\": {\n" +
            "          \"type\": \"keyword\",\n" +
            "          \"index\": true,\n" +
            "          \"store\": true\n" +
            "        },\n" +
            "        \"rateAgreementCode\": {\n" +
            "          \"type\": \"text\",\n" +
            "          \"store\": true,\n" +
            "          \"index\": true\n" +
            "        },\n" +
            "        \"effectiveStartDate\": {\n" +
            "          \"type\": \"keyword\",\n" +
            "          \"store\": true\n" +
            "        },\n" +
            "        \"effectiveEndDate\": {\n" +
            "          \"type\": \"keyword\",\n" +
            "          \"store\": true\n" +
            "        },\n" +
            "        \"suitAgtList\": {\n" +
            "          \"type\": \"text\",\n" +
            "          \"index\": true,\n" +
            "          \"store\": true,\n" +
            "          \"analyzer\": \"semicolon\"\n" +
            "        },\n" +
            "        \"publishDate\": {\n" +
            "          \"type\": \"keyword\",\n" +
            "          \"store\": true,\n" +
            "          \"index\": true\n" +
            "        },\n" +
            "        \"isEffective\": {\n" +
            "          \"type\": \"keyword\",\n" +
            "          \"store\": true,\n" +
            "          \"index\": true\n" +
            "        },\n" +
            "        \"issueAirlineCode\": {\n" +
            "          \"type\": \"keyword\",\n" +
            "          \"store\": true,\n" +
            "          \"index\": true\n" +
            "        },\n" +
            "        \"memo\": {\n" +
            "          \"type\": \"text\",\n" +
            "          \"store\": true,\n" +
            "          \"index\": false\n" +
            "        },\n" +
            "        \"calcType\": {\n" +
            "          \"type\": \"keyword\",\n" +
            "          \"store\": true,\n" +
            "          \"index\": true\n" +
            "        },\n" +
            "        \"items\": {\n" +
            "          \"type\": \"nested\",\n" +
            "          \"properties\": {\n" +
            "            \n" +
            "            \"id\": {\n" +
            "              \"type\": \"integer\",\n" +
            "              \"index\": true,\n" +
            "              \"store\": true\n" +
            "            },\n" +
            "            \"suitFeeList\": {\n" +
            "              \"type\": \"text\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true,\n" +
            "              \"analyzer\": \"semicolon\" \n" +
            "            },\n" +
            "            \"suitCarrierList\": {\n" +
            "              \"type\": \"text\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true,\n" +
            "              \"analyzer\": \"semicolon\" \n" +
            "            },\n" +
            "            \"airFlightType\": {\n" +
            "              \"type\": \"integer\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true\n" +
            "            },\n" +
            "            \"suitFlightList\": {\n" +
            "              \"type\": \"text\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true,\n" +
            "              \"analyzer\": \"semicolon\" \n" +
            "            },\n" +
            "            \"suitDateType\": {\n" +
            "              \"type\": \"keyword\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true\n" +
            "            },\n" +
            "            \"suitTimeList\": {\n" +
            "              \"type\": \"text\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true,\n" +
            "              \"analyzer\": \"semicolon\" \n" +
            "            },\n" +
            "            \"suitProductList\": {\n" +
            "              \"type\": \"text\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true,\n" +
            "              \"analyzer\":  \"semicolon\" \n" +
            "            },\n" +
            "            \"calcWeightType\": {\n" +
            "              \"type\": \"keyword\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true\n" +
            "            },\n" +
            "            \"transfersPointList\": {\n" +
            "              \"type\": \"text\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true,\n" +
            "              \"analyzer\": \"semicolon\"\n" +
            "            },\n" +
            "            \"originAirportList\": {\n" +
            "              \"type\": \"text\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true,\n" +
            "              \"analyzer\": \"semicolon\" \n" +
            "            },\n" +
            "            \"destAirportList\": {\n" +
            "              \"type\": \"text\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true,\n" +
            "              \"analyzer\": \"semicolon\" \n" +
            "            },\n" +
            "            \"calcType\": {\n" +
            "              \"type\": \"keyword\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true\n" +
            "            },\n" +
            "            \"rate\": {\n" +
            "              \"type\": \"text\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": false\n" +
            "            },\n" +
            "            \"unit\": {\n" +
            "              \"type\": \"keyword\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true\n" +
            "            },\n" +
            "            \"memo\": {\n" +
            "              \"type\": \"text\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": false\n" +
            "            },\n" +
            "            \"effectiveStartDate\": {\n" +
            "              \"type\": \"keyword\",\n" +
            "              \"store\": true\n" +
            "            },\n" +
            "            \"effectiveEndDate\": {\n" +
            "              \"type\": \"keyword\",\n" +
            "              \"store\": true\n" +
            "            },\n" +
            "            \"isEffective\": {\n" +
            "              \"type\": \"keyword\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true\n" +
            "            },\n" +
            "            \"rateType\": {\n" +
            "              \"type\": \"keyword\",\n" +
            "              \"store\": true,\n" +
            "              \"index\": true\n" +
            "            }\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}\n";


    @Override
    protected String index() {
        return index;
    }

    @Override
    protected String type() {
        return type;
    }

    @BeforeClass
    public static void beforeClass() throws IOException {
        createIndexWithMapping(index, mapping);
    }

    @AfterClass
    public static void afterClass() throws IOException {
        dropIndex(index);
    }

    @After
    public void after() throws IOException {
        deleteAllDocs();
    }

    @Test
    public void testIndexMapping2() throws IOException {
        String s = getMappingAsString();
        Map<String, Object> mapping = JsonUtils.toMap(s);
        mappingAssertion(mapping);
    }

    @Test
    public void testIndexMapping() throws IOException {
        Map<String, Object> mapping = getMapping();
        mappingAssertion(mapping);
    }

    private void mappingAssertion(Map<String, Object> mapping) {
        assertTrue(mapping.containsKey(index()));
        Map<String, Object> o1 = (Map<String, Object>) mapping.get(index());
        assertTrue(o1.containsKey("mappings"));
        Map<String, Object> o2 = (Map<String, Object>) o1.get("mappings");
        assertTrue(o2.containsKey(type));
        Map<String, Object> o3 = (Map<String, Object>) o2.get(type);
        assertTrue(o3.containsKey("properties"));
        Map<String, Object> o4 = (Map<String, Object>) o3.get("properties");
        assertTrue(o4.containsKey("items"));
        assertTrue(o4.containsKey("rateDocNo"));
        assertTrue(o4.containsKey("rateAgreementCode"));
        // ...
    }


    @Test
    public void testInsertWithId() throws IOException {
        boolean b = insertDoc("200", "{\n" +
                "  \"items\": [{\n" +
                "    \"memo\": \"\",\n" +
                "    \"calcType\": \"1\",\n" +
                "    \"destAirportList\": \"PEK\",\n" +
                "    \"originAirportList\": \"KMG\",\n" +
                "    \"transfersPointList\": \"\",\n" +
                "    \"calcWeightType\": \"0\",\n" +
                "    \"suitProductList\": \"PA001;PA002\",\n" +
                "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
                "    \"suitDateType\": \"I\",\n" +
                "    \"suitFlightList\": \"4171\",\n" +
                "    \"airFlightType\": \"0\",\n" +
                "    \"suitCarrierList\": \"B001;B002\",\n" +
                "    \"suitFeeList\": \"F001;F002\",\n" +
                "    \"effectiveEndDate\": \"20991231\",\n" +
                "    \"effectiveStartDate\": \"20180927\",\n" +
                "    \"rate\": \"{\\\"M\\\":\\\"0\\\",\\\"N\\\":\\\"9.6\\\",\\\"45\\\":\\\"2.0\\\",\\\"100\\\":\\\"1.5\\\",\\\"300\\\":\\\"1.0\\\",\\\"500\\\":\\\"0.8\\\"}\",\n" +
                "    \"unit\": \"CNY\"\n" +
                "  }, {\n" +
                "    \"memo\": \"\",\n" +
                "    \"calcType\": \"1\",\n" +
                "    \"destAirportList\": \"PEK\",\n" +
                "    \"originAirportList\": \"KMG\",\n" +
                "    \"transfersPointList\": \"LXA\",\n" +
                "    \"calcWeightType\": \"0\",\n" +
                "    \"suitProductList\": \"ALL\",\n" +
                "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
                "    \"suitDateType\": \"I\",\n" +
                "    \"suitFlightList\": \"ALL\",\n" +
                "    \"airFlightType\": \"0\",\n" +
                "    \"suitCarrierList\": \"B001;B002\",\n" +
                "    \"suitFeeList\": \"F001;F002\",\n" +
                "    \"effectiveEndDate\": \"20991231\",\n" +
                "    \"effectiveStartDate\": \"20180927\",\n" +
                "    \"rate\": \"{\\\"B\\\":\\\"8\\\",\\\"M\\\":\\\"50\\\"}\",\n" +
                "    \"unit\": \"CNY\"\n" +
                "  }],\n" +
                "  \"calcType\": \"1\",\n" +
                "  \"memo\": \"昆明国航-2017.3.26.xls\",\n" +
                "  \"auditorTime\": \"20180927\",\n" +
                "  \"auditor\": \"boss\",\n" +
                "  \"issueAirlineCode\": \"MU\",\n" +
                "  \"isEffective\": \"1\",\n" +
                "  \"publishDate\": \"20180927\",\n" +
                "  \"isPublished\": \"1\",\n" +
                "  \"suitAgtList\": \"A001;A002;\",\n" +
                "  \"effectiveStartDate\": \"20180927\",\n" +
                "  \"effectiveEndDate\": \"20991231\",\n" +
                "  \"rateDocNo\": \"国货航KMG始发国内直达销售运价201703\",\n" +
                "  \"rateAgreementCode\": \"KMG2017-R003（D）\"\n" +
                "}");
        // System.out.println(b);

        SearchResponse res = searchAll();
        assertHitCount(res, 1);
        assertSearchHit(res, 1, hasId("200"));
    }

    private SearchResponse searchAll() throws IOException {
        SearchRequest req = new SearchRequest(index());
        req.types(type());
        req.source(SearchSourceBuilder.searchSource().query(QueryBuilders.matchAllQuery()));
        return highLevelClient.search(req);
    }

    @Test
    public void testGabc() throws IOException {
        String s = "{ \"_index\" : \"test\", \"_type\" : \"_doc\", \"_id\" : \"1\" }";
        String s2 = "{ \"index\" : { \"_index\" : \"test\", \"_type\" : \"_doc\", \"_id\" : \"1\" } }";
        String s3 = String.format("{ \"create\" : %s }", s);
        System.out.println(s2);
        System.out.println(s3);
        assertEquals(s2, s3);
    }

    @Test
    public void testInsert() throws IOException {
        boolean b = insertDoc("{\n" +
                "  \"items\": [{\n" +
                "    \"memo\": \"\",\n" +
                "    \"calcType\": \"1\",\n" +
                "    \"destAirportList\": \"PEK\",\n" +
                "    \"originAirportList\": \"KMG\",\n" +
                "    \"transfersPointList\": \"\",\n" +
                "    \"calcWeightType\": \"0\",\n" +
                "    \"suitProductList\": \"PA001;PA002\",\n" +
                "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
                "    \"suitDateType\": \"I\",\n" +
                "    \"suitFlightList\": \"4171\",\n" +
                "    \"airFlightType\": \"0\",\n" +
                "    \"suitCarrierList\": \"B001;B002\",\n" +
                "    \"suitFeeList\": \"F001;F002\",\n" +
                "    \"effectiveEndDate\": \"20991231\",\n" +
                "    \"effectiveStartDate\": \"20180927\",\n" +
                "    \"rate\": \"{\\\"M\\\":\\\"0\\\",\\\"N\\\":\\\"9.6\\\",\\\"45\\\":\\\"2.0\\\",\\\"100\\\":\\\"1.5\\\",\\\"300\\\":\\\"1.0\\\",\\\"500\\\":\\\"0.8\\\"}\",\n" +
                "    \"unit\": \"CNY\"\n" +
                "  }, {\n" +
                "    \"memo\": \"\",\n" +
                "    \"calcType\": \"1\",\n" +
                "    \"destAirportList\": \"PEK\",\n" +
                "    \"originAirportList\": \"KMG\",\n" +
                "    \"transfersPointList\": \"LXA\",\n" +
                "    \"calcWeightType\": \"0\",\n" +
                "    \"suitProductList\": \"ALL\",\n" +
                "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
                "    \"suitDateType\": \"I\",\n" +
                "    \"suitFlightList\": \"ALL\",\n" +
                "    \"airFlightType\": \"0\",\n" +
                "    \"suitCarrierList\": \"B001;B002\",\n" +
                "    \"suitFeeList\": \"F001;F002\",\n" +
                "    \"effectiveEndDate\": \"20991231\",\n" +
                "    \"effectiveStartDate\": \"20180927\",\n" +
                "    \"rate\": \"{\\\"B\\\":\\\"8\\\",\\\"M\\\":\\\"50\\\"}\",\n" +
                "    \"unit\": \"CNY\"\n" +
                "  }],\n" +
                "  \"calcType\": \"1\",\n" +
                "  \"memo\": \"昆明国航-2017.3.26.xls\",\n" +
                "  \"auditorTime\": \"20180927\",\n" +
                "  \"auditor\": \"boss\",\n" +
                "  \"issueAirlineCode\": \"MU\",\n" +
                "  \"isEffective\": \"1\",\n" +
                "  \"publishDate\": \"20180927\",\n" +
                "  \"isPublished\": \"1\",\n" +
                "  \"suitAgtList\": \"A001;A002;\",\n" +
                "  \"effectiveStartDate\": \"20180927\",\n" +
                "  \"effectiveEndDate\": \"20991231\",\n" +
                "  \"rateDocNo\": \"国货航KMG始发国内直达销售运价201703\",\n" +
                "  \"rateAgreementCode\": \"KMG2017-R003（D）\"\n" +
                "}");

        SearchResponse res = searchAll();
        System.out.println(res);
        assertHitCount(res, 1);

        // test delete.
        deleteAllDocs();
        SearchResponse res2 = searchAll();
        System.out.println(res2);
        assertHitCount(res2, 0);
    }

    @Test
    public void testBatchInsert() throws IOException {
        boolean b = givenDocs("{\n" +
                "  \"items\": [{\n" +
                "    \"memo\": \"\",\n" +
                "    \"calcType\": \"1\",\n" +
                "    \"destAirportList\": \"PEK\",\n" +
                "    \"originAirportList\": \"KMG\",\n" +
                "    \"transfersPointList\": \"\",\n" +
                "    \"calcWeightType\": \"0\",\n" +
                "    \"suitProductList\": \"PA001;PA002\",\n" +
                "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
                "    \"suitDateType\": \"I\",\n" +
                "    \"suitFlightList\": \"4171\",\n" +
                "    \"airFlightType\": \"0\",\n" +
                "    \"suitCarrierList\": \"B001;B002\",\n" +
                "    \"suitFeeList\": \"F001;F002\",\n" +
                "    \"effectiveEndDate\": \"20991231\",\n" +
                "    \"effectiveStartDate\": \"20180927\",\n" +
                "    \"rate\": \"{\\\"M\\\":\\\"0\\\",\\\"N\\\":\\\"9.6\\\",\\\"45\\\":\\\"2.0\\\",\\\"100\\\":\\\"1.5\\\",\\\"300\\\":\\\"1.0\\\",\\\"500\\\":\\\"0.8\\\"}\",\n" +
                "    \"unit\": \"CNY\"\n" +
                "  }, {\n" +
                "    \"memo\": \"\",\n" +
                "    \"calcType\": \"1\",\n" +
                "    \"destAirportList\": \"PEK\",\n" +
                "    \"originAirportList\": \"KMG\",\n" +
                "    \"transfersPointList\": \"LXA\",\n" +
                "    \"calcWeightType\": \"0\",\n" +
                "    \"suitProductList\": \"ALL\",\n" +
                "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
                "    \"suitDateType\": \"I\",\n" +
                "    \"suitFlightList\": \"ALL\",\n" +
                "    \"airFlightType\": \"0\",\n" +
                "    \"suitCarrierList\": \"B001;B002\",\n" +
                "    \"suitFeeList\": \"F001;F002\",\n" +
                "    \"effectiveEndDate\": \"20991231\",\n" +
                "    \"effectiveStartDate\": \"20180927\",\n" +
                "    \"rate\": \"{\\\"B\\\":\\\"8\\\",\\\"M\\\":\\\"50\\\"}\",\n" +
                "    \"unit\": \"CNY\"\n" +
                "  }],\n" +
                "  \"calcType\": \"1\",\n" +
                "  \"memo\": \"昆明国航-2017.3.26.xls\",\n" +
                "  \"auditorTime\": \"20180927\",\n" +
                "  \"auditor\": \"boss\",\n" +
                "  \"issueAirlineCode\": \"MU\",\n" +
                "  \"isEffective\": \"1\",\n" +
                "  \"publishDate\": \"20180927\",\n" +
                "  \"isPublished\": \"1\",\n" +
                "  \"suitAgtList\": \"A001;A002;\",\n" +
                "  \"effectiveStartDate\": \"20180927\",\n" +
                "  \"effectiveEndDate\": \"20991231\",\n" +
                "  \"rateDocNo\": \"国货航KMG始发国内直达销售运价201703\",\n" +
                "  \"rateAgreementCode\": \"KMG2017-R003（D）\"\n" +
                "}"
                ,
                "{\n" +
                "  \"items\": [{\n" +
                "    \"memo\": \"\",\n" +
                "    \"calcType\": \"1\",\n" +
                "    \"destAirportList\": \"PEK\",\n" +
                "    \"originAirportList\": \"KMG\",\n" +
                "    \"transfersPointList\": \"\",\n" +
                "    \"calcWeightType\": \"0\",\n" +
                "    \"suitProductList\": \"PA001;PA002\",\n" +
                "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
                "    \"suitDateType\": \"I\",\n" +
                "    \"suitFlightList\": \"4171\",\n" +
                "    \"airFlightType\": \"0\",\n" +
                "    \"suitCarrierList\": \"B001;B002\",\n" +
                "    \"suitFeeList\": \"F001;F002\",\n" +
                "    \"effectiveEndDate\": \"20991231\",\n" +
                "    \"effectiveStartDate\": \"20180927\",\n" +
                "    \"rate\": \"{\\\"M\\\":\\\"0\\\",\\\"N\\\":\\\"9.6\\\",\\\"45\\\":\\\"2.0\\\",\\\"100\\\":\\\"1.5\\\",\\\"300\\\":\\\"1.0\\\",\\\"500\\\":\\\"0.8\\\"}\",\n" +
                "    \"unit\": \"CNY\"\n" +
                "  }, {\n" +
                "    \"memo\": \"\",\n" +
                "    \"calcType\": \"1\",\n" +
                "    \"destAirportList\": \"PEK\",\n" +
                "    \"originAirportList\": \"KMG\",\n" +
                "    \"transfersPointList\": \"LXA\",\n" +
                "    \"calcWeightType\": \"0\",\n" +
                "    \"suitProductList\": \"ALL\",\n" +
                "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
                "    \"suitDateType\": \"I\",\n" +
                "    \"suitFlightList\": \"ALL\",\n" +
                "    \"airFlightType\": \"0\",\n" +
                "    \"suitCarrierList\": \"B001;B002\",\n" +
                "    \"suitFeeList\": \"F001;F002\",\n" +
                "    \"effectiveEndDate\": \"20991231\",\n" +
                "    \"effectiveStartDate\": \"20180927\",\n" +
                "    \"rate\": \"{\\\"B\\\":\\\"8\\\",\\\"M\\\":\\\"50\\\"}\",\n" +
                "    \"unit\": \"CNY\"\n" +
                "  }],\n" +
                "  \"calcType\": \"2\",\n" +
                "  \"memo\": \"昆明国航-2017.3.28.xls\",\n" +
                "  \"auditorTime\": \"20180927\",\n" +
                "  \"auditor\": \"boss\",\n" +
                "  \"issueAirlineCode\": \"MU\",\n" +
                "  \"isEffective\": \"1\",\n" +
                "  \"publishDate\": \"20180927\",\n" +
                "  \"isPublished\": \"1\",\n" +
                "  \"suitAgtList\": \"A001;A002;\",\n" +
                "  \"effectiveStartDate\": \"20180927\",\n" +
                "  \"effectiveEndDate\": \"20991231\",\n" +
                "  \"rateDocNo\": \"国货航KMG始发国内直达销售运价201704\",\n" +
                "  \"rateAgreementCode\": \"KMG2017-R004（D）\"\n" +
                "}");

        SearchResponse res = searchAll();
        System.out.println(res);
        assertHitCount(res, 2);
    }
}
