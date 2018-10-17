package com.github.jccode.esdemo.it;

import com.github.jccode.esdemo.ESBaseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

/**
 * RateSearchIT
 *
 * @author 01372461
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RateSearchESTest extends ESBaseCase {

    private final static String INDEX = "rate_class_index";
    private final static String TYPE = "rate_class_type";
    private static final String URL = INDEX+"/"+TYPE;

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


    @Test
    public void testSimpleMatchAll() throws IOException {
        System.out.println("Hello");
        System.out.println(client);



        assertTrue(true);
    }

}
