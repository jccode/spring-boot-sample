package com.github.jccode.springsecuritydemo.zuuldemo.apigateway;

import com.github.jccode.springsecuritydemo.zuuldemo.apigateway.filter.route.OAuth2HeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Application
 *
 * @author 01372461
 */
@EnableZuulProxy
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OAuth2HeaderFilter oauth2HeaderFilter() {
        return new OAuth2HeaderFilter();
    }
}
