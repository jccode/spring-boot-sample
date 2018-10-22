package com.github.jccode.esdemo;

import com.carrotsearch.randomizedtesting.generators.RandomStrings;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.ClusterModule;
import org.elasticsearch.common.xcontent.*;
import org.hamcrest.Matcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * BaseESTest.
 * 
 * Basic usage. 
 * 
 * <pre>
 *     class SomeTest extends BaseESTest {
 *     
 *         final static String index = randomIndexName();
 *         final static String type = "your_type";
 *     
 *         @Override
 *         protected String index() {
 *             return index;
 *         }
 *    
 *         @Override
 *         protected String type() {
 *             return type;
 *         }
 *    
 *         @BeforeClass
 *         public static void beforeClass() throws IOException {
 *             createIndexWithMapping(index, mapping);
 *         }
 *    
 *         @AfterClass
 *         public static void afterClass() throws IOException {
 *             dropIndex(index);
 *         }
 *    
 *         @After
 *         public void after() throws IOException {
 *             deleteAllDocs();
 *         }
 *
 *
 *     }
 * </pre>
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
 *       - Auto clear test data of last test case.
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

    private String _index;

    private static String DEFAULT_TEST_INDEX_PREFIX = "unit_test_index_";
    private static String DEFAULT_TEST_INDEX_TYPE = "_doc";

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

    protected static String randomIndexName() {
        return DEFAULT_TEST_INDEX_PREFIX + RandomStrings.randomAsciiAlphanumOfLength(random, 10).toLowerCase();
    }

    /**
     * Elasticsearch index for test purpose.
     * By default, it will generate a random index name, by the pattern "es_test_index_xxxxxx";
     *
     * @return
     */
    protected String index() {
        if (_index == null) {
            _index = randomIndexName();
        }
        return _index;
    }

    protected String type() {
        return DEFAULT_TEST_INDEX_TYPE;
    }

    protected static boolean createIndex(String index) throws IOException {
        Response res = restClient.performRequest("PUT", "/" + index);
        return responseIsOk(res);
    }

    protected boolean createIndex() throws IOException {
        return createIndex(index());
    }

    protected static boolean dropIndex(String index) throws IOException {
        Response res = restClient.performRequest("DELETE", "/" + index);
        return responseIsOk(res);
    }

    protected boolean dropIndex() throws IOException {
        return dropIndex(index());
    }

    protected static boolean createMapping(String index, String source) throws IOException {
        NStringEntity entity = new NStringEntity(source, ContentType.APPLICATION_JSON);
        Response res = restClient.performRequest("PUT", "/" + index, Collections.emptyMap(), entity);
        return responseIsOk(res);
    }

    protected boolean createMapping(String source) throws IOException {
        return createMapping(index(), source);
    }

    protected static boolean createIndexWithMapping(String index, String mappingSource) throws IOException {
        return createMapping(index, mappingSource);
    }

    protected boolean createIndexWithMapping(String mappingSource) throws IOException {
        return createMapping(index(), mappingSource);
    }

    protected Map<String, Object> getMapping() throws IOException {
        Response res = restClient.performRequest("GET", "/" + index() + "/_mapping");
        return entityAsMap(res);
    }

    protected String getMappingAsString() throws IOException {
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

    protected String url(String... segments) {
        List<String> els = new ArrayList<>(Arrays.asList(index(), type()));
        els.addAll(Arrays.asList(segments));
        return "/" + String.join("/", els);
    }

    protected boolean insertDoc(String id, String doc) throws IOException {
        NStringEntity entity = new NStringEntity(doc, ContentType.APPLICATION_JSON);
        Response res = restClient.performRequest("PUT", url(id)+"?refresh", Collections.emptyMap(), entity);
        return res.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED;
    }

    protected boolean insertDoc(String doc) throws IOException {
        NStringEntity entity = new NStringEntity(doc, ContentType.APPLICATION_JSON);
        Response res = restClient.performRequest("POST", url()+"?refresh", Collections.emptyMap(), entity);
        return res.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED;
    }

    protected boolean deleteAllDocs() throws IOException {
        String q = "{\"query\": {\"match_all\": {}}}";
        NStringEntity entity = new NStringEntity(q, ContentType.APPLICATION_JSON);
        Response res = restClient.performRequest("POST", url("_delete_by_query")+"?refresh", Collections.emptyMap(), entity);
        return responseIsOk(res);
    }

    /**
     * batch insert. use bulk api.
     * @param docs
     * @return
     */
    protected boolean givenDocs(String... docs) throws IOException {
        final AtomicInteger i = new AtomicInteger(0);
        String bulkSource = Stream.of(docs).map(x -> String.format("%s\n%s", bulkActionAndMetaData(i.incrementAndGet()+""), x.replaceAll("[\r\n]+", " "))).collect(Collectors.joining("\n")) + "\n";
        //System.out.println(bulkSource);
        NStringEntity entity = new NStringEntity(bulkSource, ContentType.APPLICATION_JSON);
        Response res = restClient.performRequest("POST", url("_bulk") + "?refresh", Collections.emptyMap(), entity);
        return responseIsOk(res);
    }

    private String bulkActionAndMetaData(String id) {
        return String.format("{ \"index\" : { \"_index\" : \"%s\", \"_type\" : \"%s\", \"_id\" : \"%s\" } }", index(), type(), id);
    }


    // Assertions, matchers

    protected void assertHitsCount(SearchResponse res, Matcher<Integer> matcher) {
        matcher.matches(res.getHits().getHits().length);
    }
}
