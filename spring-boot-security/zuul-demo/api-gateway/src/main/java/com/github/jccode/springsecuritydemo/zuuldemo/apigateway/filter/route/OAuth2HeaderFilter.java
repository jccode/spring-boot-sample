package com.github.jccode.springsecuritydemo.zuuldemo.apigateway.filter.route;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
public class OAuth2HeaderFilter extends ZuulFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TYPE = "Bearer";

    @Value("${oauth2-header-filter.ignore-paths}")
    private String ignorePaths;

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest req = context.getRequest();
        String uri = req.getRequestURI();
        String[] paths = StringUtils.trimArrayElements(StringUtils.commaDelimitedListToStringArray(ignorePaths));
        for (String pattern : paths) {
            if (PatternMatchUtils.simpleMatch(pattern, uri)) {
                debug("{} matched the ignored path, pass. ignore-paths: {}", uri, Arrays.toString(paths));
                return false;
            }
        }
        debug("{} do not matched any ignored path. ignore-paths: {}", uri, Arrays.toString(paths));
        return true;
    }

    @Override
    public Object run() {
        // Check oauth2 bearer token, Reject the request without a bearer token.
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest req = context.getRequest();
        String authHeader = req.getHeader(AUTHORIZATION_HEADER);
        if (authHeader != null && authHeader.startsWith(BEARER_TYPE)) {
            debug("Check Authorization header ({}) success.", authHeader);
        } else {
            // reject request
            debug("Not Authorization header found. Reject.");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            context.setResponseBody("Access is denied, authentication is required.");
        }
        return null;
    }

    private void debug(String format, Object... arguments) {
        if (log.isDebugEnabled()) log.debug(format, arguments);
    }
}
