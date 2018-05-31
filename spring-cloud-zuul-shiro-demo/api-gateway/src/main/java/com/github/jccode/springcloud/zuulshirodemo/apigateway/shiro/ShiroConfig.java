package com.github.jccode.springcloud.zuulshirodemo.apigateway.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public RestAuthenticationFilter restAuthenticationFilter() {
        return new RestAuthenticationFilter();
    }

    @Bean
    public Realm realm() {
        Ini ini = Ini.fromResourcePath("classpath:shiro.ini");
        return new IniRealm(ini);
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(@Value("${shiro.loginUrl:/login}") String loginUrl) {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition(loginUrl, "auth");
        chainDefinition.addPathDefinition("/**", "auth");
        return chainDefinition;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            SecurityManager securityManager,
            ShiroFilterChainDefinition chainDefinition,
            @Value("${shiro.loginUrl:/login}") String loginUrl,
            @Value("${shiro.successUrl:/login-success}") String successUrl,
            @Value("${shiro.unauthorizedUrl:/unauthrized}") String unauthorizedUrl) {

        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setFilterChainDefinitionMap(chainDefinition.getFilterChainMap());

        Map<String, Filter> filters = new HashMap<>();
        filters.put("auth", new RestAuthenticationFilter());
        filterFactoryBean.setFilters(filters);

        filterFactoryBean.setLoginUrl(loginUrl);
        filterFactoryBean.setSuccessUrl(successUrl);
        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        return filterFactoryBean;
    }

}
