package com.github.jccode.esdemo;

import com.carrotsearch.randomizedtesting.generators.RandomStrings;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.ClusterModule;
import org.elasticsearch.common.xcontent.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

/**
 * BaseESTest.
 *
 * It use an external elasticsearch server, and run test on top of it.
 * Do not need to dependent on `elasticsearch-maven-plugin`.
 *
 *
 * What it does:
 *
 *
 *     BeforeClass:
 *       - Create index, type
 *       - Instance client
 *
 *     Before:
 *       - Given some data, expected data logic is correct.
 *       - Given can be implement as a function.
 *
 *     After:
 *       - Auto test data of last test case.
 *
 *     AfterClass:
 *       - Close client
 *       - Delete index
 *
 *
 * @author 01372461
 */
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        ESBaseCase.class
})
@ContextConfiguration
public class ESBaseCase extends AbstractTestExecutionListener {

    protected static RestHighLevelClient highLevelClient;

    protected static RestClient restClient;

    private static String _index;

    private static String DEFAULT_TEST_INDEX_PREFIX = "unit_test_index_";

    private static Random random = new Random();


    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        highLevelClient = testContext.getApplicationContext().getBean(RestHighLevelClient.class);
        restClient = testContext.getApplicationContext().getBean(RestClient.class);
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        if (highLevelClient != null) {
            highLevelClient.close();
        }
        if (restClient != null) {
            restClient.close();
        }
    }

    /**
     * Elasticsearch index for test purpose.
     * By default, it will generate a random index name, by the pattern "es_test_index_xxxxxx";
     *
     * @return
     */
    protected static String index() {
        if (_index == null) {
            _index = DEFAULT_TEST_INDEX_PREFIX + RandomStrings.randomAsciiAlphanumOfLength(random, 10).toLowerCase();
        }
        return _index;
    }

    protected static boolean createIndex() throws IOException {
        Response res = restClient.performRequest("PUT", "/" + index());
        return responseIsOk(res);
    }

    protected static boolean dropIndex() throws IOException {
        Response res = restClient.performRequest("DELETE", "/" + index());
        return responseIsOk(res);
    }

    protected static boolean createMapping(String source) throws IOException {
        NStringEntity entity = new NStringEntity(source, ContentType.APPLICATION_JSON);
        Response res = restClient.performRequest("PUT", "/" + index(), Collections.emptyMap(), entity);
        return responseIsOk(res);
    }

    protected static boolean createIndexWithMapping(String mappingSource) throws IOException {
        return createMapping(mappingSource);
    }

    protected static Map<String, Object> getMapping() throws IOException {
        Response res = restClient.performRequest("GET", "/" + index() + "/_mapping");
        return entityAsMap(res);
    }

    protected static String getMappingAsString() throws IOException {
        Response res = restClient.performRequest("GET", "/" + index() + "/_mapping");
        return EntityUtils.toString(res.getEntity());
    }

    protected static boolean responseIsOk(Response res) {
        return res.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
    }


    public static Map<String, Object> entityAsMap(Response response) throws IOException {
        XContentType xContentType = XContentType.fromMediaTypeOrFormat(response.getEntity().getContentType().getValue());
        try (XContentParser parser = createParser(xContentType.xContent(), response.getEntity().getContent())) {
            return parser.map();
        }
    }

    /**
     * Create a new {@link XContentParser}.
     */
    protected static final XContentParser createParser(XContent xContent, InputStream data) throws IOException {
        return xContent.createParser(xContentRegistry(), LoggingDeprecationHandler.INSTANCE, data);
    }

    protected static NamedXContentRegistry xContentRegistry() {
        return new NamedXContentRegistry(ClusterModule.getNamedXWriteables());
    }
}
