package com.github.jccode.springsecuritydemo.zuuldemo.backend2.config;

import com.github.jccode.springsecuritydemo.zuuldemo.common.feign.PassHeadersFeignRequestInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FeignConfig
 *
 * @author 01372461
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext clientContext, OAuth2ProtectedResourceDetails resource) {
//        return new OAuth2FeignRequestInterceptor(clientContext, resource);
//    }

    @Bean
    public RequestInterceptor passHeadersRequestInterceptor() {
        return new PassHeadersFeignRequestInterceptor();
    }

}
