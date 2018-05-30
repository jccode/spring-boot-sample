package com.github.jccode.springbootshiro.webdemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RestAuthenticationFilter extends FormAuthenticationFilter {

    public static final String LOGIN_RESULT = "LoginResult";
    private static final String MESSAGE = "Access denied.";

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                // GET method on 'login' is not allow
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, MESSAGE);
                return false;
            }
        } else {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_FORBIDDEN, MESSAGE);
            return false;
        }
    }

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        // set login result
        setLoginResult(request, true);
        // continue the chain, loginController can return arbitrary data it need.
        return true;
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug( "Authentication exception", e );
        }

        // set login result
        setLoginResult(request, false);
        //login failed, let request continue back to the login page:
        return true;
    }

    protected void setLoginResult(ServletRequest request, boolean result) {
        WebUtils.toHttp(request).setAttribute(LOGIN_RESULT, result);
    }
}
