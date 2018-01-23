package com.github.jccode.springbootextendtestmatcher.test;

import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.StringStartsWith;
import org.springframework.test.util.JsonPathExpectationsHelper;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class ExtractJsonPathResultMatchers extends JsonPathResultMatchers {

    private final JsonPathExpectationsHelper jsonPathHelper;

    private String prefix;

    private final JsonPath jsonPath;

    protected ExtractJsonPathResultMatchers(String expression, Object... args) {
        super(expression, args);
        this.jsonPathHelper = new JsonPathExpectationsHelper(expression, args);
        this.jsonPath = JsonPath.compile("payload"); // result
    }

    @Override
    public JsonPathResultMatchers prefix(String prefix) {
        super.prefix(prefix);
        this.prefix = prefix;
        return this;
    }

    @Override
    public <T> ResultMatcher value(Matcher<T> matcher) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                String content = getContent(result);
                String content2 = extractContent(content);
                jsonPathHelper.assertValue(content2, matcher);
            }
        };
    }

    private String getContent(MvcResult result) throws UnsupportedEncodingException {
        String content = result.getResponse().getContentAsString();
        if (StringUtils.hasLength(this.prefix)) {
            try {
                String reason = String.format("Expected a JSON payload prefixed with \"%s\" but found: %s",
                        this.prefix, StringUtils.quote(content.substring(0, this.prefix.length())));
                MatcherAssert.assertThat(reason, content, StringStartsWith.startsWith(this.prefix));
                return content.substring(this.prefix.length());
            }
            catch (StringIndexOutOfBoundsException ex) {
                throw new AssertionError("JSON prefix \"" + this.prefix + "\" not found: " + ex);
            }
        }
        else {
            return content;
        }
    }

    private String extractContent(String content) {
        String result = jsonPath.read(content);
        byte[] decode = Base64.getDecoder().decode(result);
        return new String(decode);
    }

}
