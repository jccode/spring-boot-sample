package com.github.jccode.springsecuritydemo.zuuldemo.backend.config;

import com.github.jccode.springsecuritydemo.zuuldemo.common.SecurityCommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Import(SecurityCommonConfig.class)
@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "backend";

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).tokenStore(new JdbcTokenStore(dataSource));
    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//    }

}
