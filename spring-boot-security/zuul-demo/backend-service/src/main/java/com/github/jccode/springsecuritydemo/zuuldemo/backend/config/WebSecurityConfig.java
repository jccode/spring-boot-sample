package com.github.jccode.springsecuritydemo.zuuldemo.backend.config;

import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;
import com.github.jccode.springsecurity.common.authentication.RESTAuthenticationEntryPoint;
import com.github.jccode.springsecurity.common.authentication.RESTAuthenticationFailureHandler;
import com.github.jccode.springsecurity.common.authentication.RESTAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;

/**
 * WebSecurityConfig
 *
 * @author 01372461
 */
@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .formLogin().successHandler(new RESTAuthenticationSuccessHandler())
                .failureHandler(new RESTAuthenticationFailureHandler())
                .permitAll()
            .and()
                .logout()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .permitAll()
            .and()
                .addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class)
                .exceptionHandling().authenticationEntryPoint(new RESTAuthenticationEntryPoint())
            .and()
                .httpBasic();
        // @formatter:on
    }
}
