package com.github.jccode.springsecuritydemo.guide.rest;

import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

/**
 * RESTAuthenticationEntryPoint
 *
 * @author 01372461
 */
@Component
public class RESTAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {
}
