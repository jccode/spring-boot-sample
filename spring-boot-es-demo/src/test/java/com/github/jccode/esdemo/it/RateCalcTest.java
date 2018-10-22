package com.github.jccode.esdemo.it;

import com.github.jccode.esdemo.ESBaseCase;
import com.github.jccode.esdemo.dto.RateCalcQueryDto;
import com.github.jccode.esdemo.service.ESQueryBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
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

import static org.hamcrest.Matchers.*;
import static org.elasticsearch.test.hamcrest.ElasticsearchAssertions.*;
import static org.junit.Assert.*;

/**
 * RateCalcTest
 *
 * <p>
 *
 * 优先级条件:
 * - C1: 代理人 (suitAgtList)
 * - C2: 中转点 (transfersPointList)
 * - C3: 航班号 (suitFlightList)
 *
 * </p>
 *
 * <p>
 *
 * 假设: <code>_</code> 表示此条件不满足, 则3个条件可以组合出:
 * - P1: C1,C2,C3
 * - P2: C1,C2, _
 * - P3: C1, _,C3
 * - P4: C1, _, _
 * - P5: _ ,C2,C3
 * - P6: _ ,C2, _
 * - P7: _ , _,C3
 *
 * 要求: P1 > P2 > P3 > P4 > P5 > P6 > P7
 *
 * </p>
 *
 *
 * <p>
 *
 * 当存在多个运价文件时,
 * 要求,主文档的"开始生效日期"(effectiveStartDate, 记为D)越大,则优先以它为准. 即,
 *
 * 当: D1 > D2,
 * 则: (P7|D1) > (P1|D2)
 *
 * </p>
 *
 * @author 01372461
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RateCalcTest extends ESBaseCase {

    final static String index = randomIndexName();
    final static String type = "rate_class_type";
    final static String mapping = "{ \"settings\": { \"number_of_shards\": 3, \"number_of_replicas\": 2, \"analysis\": { \"analyzer\": { \"semicolon\": { \"type\": \"pattern\", \"pattern\": \";\", \"lowercase\": false } } } },  \"mappings\": {  \"rate_class_type\": { \"dynamic\":false, \"dynamic_date_formats\":[\"yyyy-MM-dd hh:mm:ss\", \"yyyy-MM-dd\" ], \"properties\": { \"id\": { \"type\": \"long\", \"index\": true, \"store\": true }, \"rateDocNo\": { \"type\": \"keyword\", \"index\": true, \"store\": true }, \"rateAgreementCode\": { \"type\": \"text\", \"store\": true, \"index\": true }, \"effectiveStartDate\": { \"type\": \"keyword\", \"store\": true }, \"effectiveEndDate\": { \"type\": \"keyword\", \"store\": true }, \"suitAgtList\": { \"type\": \"text\", \"index\": true, \"store\": true, \"analyzer\": \"semicolon\" }, \"publishDate\": { \"type\": \"keyword\", \"store\": true, \"index\": true }, \"isEffective\": { \"type\": \"keyword\", \"store\": true, \"index\": true }, \"issueAirlineCode\": { \"type\": \"keyword\", \"store\": true, \"index\": true }, \"memo\": { \"type\": \"text\", \"store\": true, \"index\": false }, \"calcType\": { \"type\": \"keyword\", \"store\": true, \"index\": true }, \"items\": { \"type\": \"nested\", \"properties\": {  \"id\": { \"type\": \"long\", \"index\": true, \"store\": true }, \"rateClassId\": { \"type\": \"long\", \"index\": true, \"store\": true }, \"suitFeeList\": { \"type\": \"text\", \"store\": true, \"index\": true, \"analyzer\": \"semicolon\" }, \"suitCarrierList\": { \"type\": \"text\", \"store\": true, \"index\": true, \"analyzer\": \"semicolon\" }, \"airFlightType\": { \"type\": \"integer\", \"store\": true, \"index\": true }, \"suitFlightList\": { \"type\": \"text\", \"store\": true, \"index\": true, \"analyzer\": \"semicolon\" }, \"suitDateType\": { \"type\": \"keyword\", \"store\": true, \"index\": true }, \"suitTimeList\": { \"type\": \"text\", \"store\": true, \"index\": true, \"analyzer\": \"semicolon\" }, \"suitProductList\": { \"type\": \"text\", \"store\": true, \"index\": true, \"analyzer\": \"semicolon\" }, \"calcWeightType\": { \"type\": \"keyword\", \"store\": true, \"index\": true }, \"transfersPointList\": { \"type\": \"text\", \"store\": true, \"index\": true, \"analyzer\": \"semicolon\" }, \"originAirportList\": { \"type\": \"text\", \"store\": true, \"index\": true, \"analyzer\": \"semicolon\" }, \"destAirportList\": { \"type\": \"text\", \"store\": true, \"index\": true, \"analyzer\": \"semicolon\" }, \"calcType\": { \"type\": \"keyword\", \"store\": true, \"index\": true }, \"rate\": { \"type\": \"text\", \"store\": true, \"index\": false }, \"unit\": { \"type\": \"keyword\", \"store\": true, \"index\": true }, \"memo\": { \"type\": \"text\", \"store\": true, \"index\": false }, \"effectiveStartDate\": { \"type\": \"keyword\", \"store\": true }, \"effectiveEndDate\": { \"type\": \"keyword\", \"store\": true }, \"isEffective\": { \"type\": \"keyword\", \"store\": true, \"index\": true }, \"rateType\": { \"type\": \"keyword\", \"store\": true, \"index\": true } } } } } } }\n";
    final static String data0 = "{\n" +
            "  \"items\": [{\n" +
            "    \"id\": \"1001\",\n" +
            "    \"rateClassId\": \"100\",\n" +
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
            "    \"id\": \"1002\",\n" +
            "    \"rateClassId\": \"100\",\n" +
            "    \"memo\": \"\",\n" +
            "    \"calcType\": \"1\",\n" +
            "    \"destAirportList\": \"PEK\",\n" +
            "    \"originAirportList\": \"KMG\",\n" +
            "    \"transfersPointList\": \"SHA\",\n" +
            "    \"calcWeightType\": \"0\",\n" +
            "    \"suitProductList\": \"PB001;PB002\",\n" +
            "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
            "    \"suitDateType\": \"I\",\n" +
            "    \"suitFlightList\": \"ALL\",\n" +
            "    \"airFlightType\": \"0\",\n" +
            "    \"suitCarrierList\": \"B001;B002\",\n" +
            "    \"suitFeeList\": \"F001;F002\",\n" +
            "    \"effectiveEndDate\": \"20991231\",\n" +
            "    \"effectiveStartDate\": \"20180927\",\n" +
            "    \"rate\": \"{\\\"M\\\":\\\"0\\\",\\\"N\\\":\\\"9.2\\\",\\\"45\\\":\\\"1.8\\\",\\\"100\\\":\\\"1.5\\\",\\\"300\\\":\\\"1.0\\\",\\\"500\\\":\\\"0.8\\\"}\",\n" +
            "    \"unit\": \"CNY\"\n" +
            "  }, {\n" +
            "    \"id\": \"1003\",\n" +
            "    \"rateClassId\": \"100\",\n" +
            "    \"memo\": \"\",\n" +
            "    \"calcType\": \"1\",\n" +
            "    \"destAirportList\": \"PEK\",\n" +
            "    \"originAirportList\": \"KMG\",\n" +
            "    \"transfersPointList\": \"LXA\",\n" +
            "    \"calcWeightType\": \"0\",\n" +
            "    \"suitProductList\": \"PA001;PA002;PB001;PB002\",\n" +
            "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
            "    \"suitDateType\": \"I\",\n" +
            "    \"suitFlightList\": \"4172;4173\",\n" +
            "    \"airFlightType\": \"0\",\n" +
            "    \"suitCarrierList\": \"B001;B002\",\n" +
            "    \"suitFeeList\": \"F001;F002\",\n" +
            "    \"effectiveEndDate\": \"20991231\",\n" +
            "    \"effectiveStartDate\": \"20180927\",\n" +
            "    \"rate\": \"{\\\"A\\\":\\\"50\\\"}\",\n" +
            "    \"unit\": \"CNY\"\n" +
            "  }, {\n" +
            "    \"id\": \"1004\",\n" +
            "    \"rateClassId\": \"100\",\n" +
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
            "  \"id\": \"100\",\n" +
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
            "}";

    final String data1 = "{\n" +
            "  \"items\": [{\n" +
            "    \"id\": \"1011\",\n" +
            "    \"rateClassId\": \"101\",\n" +
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
            "    \"id\": \"1012\",\n" +
            "    \"rateClassId\": \"101\",\n" +
            "    \"memo\": \"\",\n" +
            "    \"calcType\": \"1\",\n" +
            "    \"destAirportList\": \"PEK\",\n" +
            "    \"originAirportList\": \"KMG\",\n" +
            "    \"transfersPointList\": \"\",\n" +
            "    \"calcWeightType\": \"0\",\n" +
            "    \"suitProductList\": \"PB001;PB002\",\n" +
            "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
            "    \"suitDateType\": \"I\",\n" +
            "    \"suitFlightList\": \"4172;4173\",\n" +
            "    \"airFlightType\": \"0\",\n" +
            "    \"suitCarrierList\": \"B001;B002\",\n" +
            "    \"suitFeeList\": \"F001;F002\",\n" +
            "    \"effectiveEndDate\": \"20991231\",\n" +
            "    \"effectiveStartDate\": \"20180927\",\n" +
            "    \"rate\": \"{\\\"M\\\":\\\"0\\\",\\\"N\\\":\\\"9.2\\\",\\\"45\\\":\\\"1.8\\\",\\\"100\\\":\\\"1.5\\\",\\\"300\\\":\\\"1.0\\\",\\\"500\\\":\\\"0.8\\\"}\",\n" +
            "    \"unit\": \"CNY\"\n" +
            "  }, {\n" +
            "    \"id\": \"1013\",\n" +
            "    \"rateClassId\": \"101\",\n" +
            "    \"memo\": \"\",\n" +
            "    \"calcType\": \"1\",\n" +
            "    \"destAirportList\": \"PEK\",\n" +
            "    \"originAirportList\": \"KMG\",\n" +
            "    \"transfersPointList\": \"LXA\",\n" +
            "    \"calcWeightType\": \"0\",\n" +
            "    \"suitProductList\": \"PA001;PA002;PB001;PB002\",\n" +
            "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
            "    \"suitDateType\": \"I\",\n" +
            "    \"suitFlightList\": \"4172;4173\",\n" +
            "    \"airFlightType\": \"0\",\n" +
            "    \"suitCarrierList\": \"B001;B002\",\n" +
            "    \"suitFeeList\": \"F001;F002\",\n" +
            "    \"effectiveEndDate\": \"20991231\",\n" +
            "    \"effectiveStartDate\": \"20180927\",\n" +
            "    \"rate\": \"{\\\"A\\\":\\\"50\\\"}\",\n" +
            "    \"unit\": \"CNY\"\n" +
            "  }, {\n" +
            "    \"id\": \"1014\",\n" +
            "    \"rateClassId\": \"101\",\n" +
            "    \"memo\": \"\",\n" +
            "    \"calcType\": \"1\",\n" +
            "    \"destAirportList\": \"PEK\",\n" +
            "    \"originAirportList\": \"KMG\",\n" +
            "    \"transfersPointList\": \"LXA\",\n" +
            "    \"calcWeightType\": \"0\",\n" +
            "    \"suitProductList\": \"PA001\",\n" +
            "    \"suitTimeList\": \"20180927;20180928;20180929;20180930\",\n" +
            "    \"suitDateType\": \"I\",\n" +
            "    \"suitFlightList\": \"4174\",\n" +
            "    \"airFlightType\": \"0\",\n" +
            "    \"suitCarrierList\": \"B001;B002\",\n" +
            "    \"suitFeeList\": \"F001;F002\",\n" +
            "    \"effectiveEndDate\": \"20991231\",\n" +
            "    \"effectiveStartDate\": \"20180927\",\n" +
            "    \"rate\": \"{\\\"B\\\":\\\"8\\\",\\\"M\\\":\\\"50\\\"}\",\n" +
            "    \"unit\": \"CNY\"\n" +
            "  }],\n" +
            "  \"id\": \"101\",\n" +
            "  \"calcType\": \"1\",\n" +
            "  \"memo\": \"昆明国航-2017.3.26.xls\",\n" +
            "  \"auditorTime\": \"20180927\",\n" +
            "  \"auditor\": \"boss\",\n" +
            "  \"issueAirlineCode\": \"MU\",\n" +
            "  \"isEffective\": \"1\",\n" +
            "  \"publishDate\": \"20180927\",\n" +
            "  \"isPublished\": \"1\",\n" +
            "  \"suitAgtList\": \"A001;A004\",\n" +
            "  \"effectiveStartDate\": \"20180927\",\n" +
            "  \"effectiveEndDate\": \"20991231\",\n" +
            "  \"rateDocNo\": \"国货航KMG始发国内直达销售运价201703\",\n" +
            "  \"rateAgreementCode\": \"KMG2017-R003（D）\"\n" +
            "}";

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

    /**
     * 构造查询参数
     *
     * 过滤条件,固定为:
     * originAirportList = KMG;
     * destAirportList = PEK;
     * date = 20180928;
     *
     * @return
     */
    private RateCalcQueryDto param(String suitAgtList, String transfersPointList, String suitFlightList) {
        RateCalcQueryDto param = new RateCalcQueryDto();
        param.setDate("20180928");
        param.setOriginAirportList("KMG");
        param.setDestAirportList("PEK");
        param.setSuitAgtList(suitAgtList);
        param.setTransfersPointList(transfersPointList);
        param.setSuitFlightList(suitFlightList);
        return param;
    }

    private SearchResponse search(SearchSourceBuilder sourceBuilder) throws IOException {
        SearchRequest req = new SearchRequest(index());
        req.types(type());
        req.source(sourceBuilder);
        SearchResponse res = highLevelClient.search(req);
        return res;
    }

    private SearchHits innerHits(SearchResponse res) {
        return res.getHits().getHits()[0].getInnerHits().get("items");
    }

    private void assertInnerHitsFirstHasId(SearchResponse res, String id) {
        assertHitsCount(res, greaterThan(0));
        SearchHits innerHits = innerHits(res);
        float innerMaxScore = innerHits.getMaxScore();
        SearchHit[] innerHitsArr = innerHits.getHits();
        assertThat(innerHitsArr.length, greaterThan(0));
        assertThat(innerHitsArr[0].getScore(), is(innerMaxScore));
        Map<String, Object> i0 = innerHitsArr[0].getSourceAsMap();
        assertThat(i0.get("id"), is(id));
    }

    @Test
    public void test_P1_gt_P2_in_single_doc() throws IOException {
        givenDocs(data0);

        // Assert P1 > P2.  (C1, C2, C3) > (C1, C2, _)  | sp(4172) > ALL
        SearchResponse res = search(ESQueryBuilder.ratePriorityQuery(param("A001", "LXA", "4172")));
        assertInnerHitsFirstHasId(res, "1003");  // 1003 > 1004


        // Assert P1 > P2.  (C1, C2, C3) > (C1, C2, _)  | ALL > _
        SearchResponse res2 = search(ESQueryBuilder.ratePriorityQuery(param("A001", "LXA", "4171")));
        assertInnerHitsFirstHasId(res2, "1004");   // 1004 > 1003
    }

    @Test
    public void test_P1_gt_P2_across_docs() throws IOException {
        givenDocs(data0, data1);

        // Assert P1 > P2. (C1, C2, C3) > (C1, C2, _)   | sp(4174) > ALL > _
        SearchResponse res = search(ESQueryBuilder.ratePriorityQuery(param("A001", "LXA", "4174")));
        assertInnerHitsFirstHasId(res, "1014");   // 1014 > 1003|1004

        // Assert P1 > P2. (C1, C2, C3) > (C1, C2, _)   | ALL > _
        SearchResponse res2 = search(ESQueryBuilder.ratePriorityQuery(param("A001", "LXA", "4175")));
        assertInnerHitsFirstHasId(res2, "1004");
    }

    @Test
    public void test_P2_gt_P3_in_single_doc() throws IOException {
        givenDocs(data1);

        // Assert P2 > P3. (C1, C2, _) > (C1, _, C2)
        SearchResponse res = search(ESQueryBuilder.ratePriorityQuery(param("A001", "LXA", "4171")));
        System.out.println(res);
        // expect: 1013, 1014 has same score(top score)

        assertHitsCount(res, greaterThan(1));
        SearchHits items = innerHits(res);
        float innerMaxScore = items.getMaxScore();
        SearchHit[] hitsArr = items.getHits();

        assertThat(hitsArr[0].getScore(), is(innerMaxScore));
        assertThat(hitsArr[1].getScore(), is(innerMaxScore));

        Map<String, Object> i0 = hitsArr[0].getSourceAsMap();
        Map<String, Object> i1 = hitsArr[1].getSourceAsMap();
        assertThat(i0.get("id"), isIn(new String[]{"1013", "1014"}));
        assertThat(i1.get("id"), isIn(new String[]{"1013", "1014"}));
    }

    @Test
    public void test_P3_gt_P4_in_single_doc() throws IOException {
        givenDocs(data1);

        // Assert P3 > P4.   (C1, _, C2) > (C1, _, _)
        SearchResponse res = search(ESQueryBuilder.ratePriorityQuery(param("A001", "CAN", "4174")));
        assertInnerHitsFirstHasId(res, "1014");  // 1014 > 1011
    }

    @Test
    public void test_P4_gt_P5_cross_docs() throws IOException {
        givenDocs(data0, data1);

        // Assert P4 > P5.    (C1, _, _) > (_, C2, C3)
        SearchResponse res = search(ESQueryBuilder.ratePriorityQuery(param("A004", "SHA", "4175")));
        // expect items of 101 (1011,1012,1013,1014) > 1002

        assertHitsCount(res, greaterThan(4));
        SearchHits items = innerHits(res);
        float innerMaxScore = items.getMaxScore();
        SearchHit[] hitsArr = items.getHits();

        assertThat(hitsArr[0].getScore(), is(innerMaxScore));
        assertThat(hitsArr[1].getScore(), is(innerMaxScore));
        assertThat(hitsArr[2].getScore(), is(innerMaxScore));
        assertThat(hitsArr[3].getScore(), is(innerMaxScore));

        Map<String, Object> i0 = hitsArr[0].getSourceAsMap();
        Map<String, Object> i1 = hitsArr[1].getSourceAsMap();
        Map<String, Object> i2 = hitsArr[2].getSourceAsMap();
        Map<String, Object> i3 = hitsArr[3].getSourceAsMap();

        assertThat(i0.get("id"), isIn(new String[]{"1011", "1012", "1013", "1014"}));
        assertThat(i1.get("id"), isIn(new String[]{"1011", "1012", "1013", "1014"}));
        assertThat(i2.get("id"), isIn(new String[]{"1011", "1012", "1013", "1014"}));
        assertThat(i3.get("id"), isIn(new String[]{"1011", "1012", "1013", "1014"}));
    }

    @Test
    public void test_P5_gt_P6_in_single_doc() throws IOException {
        givenDocs(data1);

        // Assert P5 > P6.    (_ , C2, C3) > (_, C2, _)
        SearchResponse res = search(ESQueryBuilder.ratePriorityQuery(param("A005", "LXA", "4174")));
        assertInnerHitsFirstHasId(res, "1014");  // 1014 > 1013
    }

    @Test
    public void test_P6_gt_P7_in_single_doc() throws IOException {
        givenDocs(data1);

        // Assert P6 > P7.   (_, C2, _) > (_, _, C3)
        SearchResponse res = search(ESQueryBuilder.ratePriorityQuery(param("A005", "LXA", "4171")));
        assertInnerHitsFirstHasId(res, "1014");  // 1014 > 1011
    }
}
