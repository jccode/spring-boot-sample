package com.github.jccode.esdemo;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * BaseESTest
 *
 * @author 01372461
 */
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        ESBaseCase.class
})
@ContextConfiguration
public class ESBaseCase extends AbstractTestExecutionListener {

    protected static RestHighLevelClient client;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        System.out.println("before class");
        client = testContext.getApplicationContext().getBean(RestHighLevelClient.class);
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        System.out.println("after class");
        if (client != null) {
            client.close();
        }
    }

}
