package com.github.jccode.esdemo.it;

import com.github.jccode.esdemo.TestApplicationConfiguration;
import com.github.jccode.esdemo.config.ElasticSearchConfigItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

/**
 * HelloTest
 *
 * @author 01372461
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class HelloTest {

    @Autowired
    private ElasticSearchConfigItem configItem;

    @Test
    public void testProperties() {
        System.out.println(configItem);
        assertTrue(true);
    }
}
