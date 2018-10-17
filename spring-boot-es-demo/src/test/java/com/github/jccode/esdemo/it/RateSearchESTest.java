package com.github.jccode.esdemo.it;

import com.github.jccode.esdemo.ESBaseCase;
import com.github.jccode.esdemo.util.JsonUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

/**
 * RateSearchESTest
 *
 * @author 01372461
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RateSearchESTest extends ESBaseCase {

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

    final static String type = "rate_class_type";

    @BeforeClass
    public static void beforeClass() throws IOException {
        createIndexWithMapping(mapping);
    }

    @AfterClass
    public static void afterClass() throws IOException {
        dropIndex();
    }


    @Test
    public void testSimpleMatchAll() throws IOException {
        assertTrue(true);
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

}
