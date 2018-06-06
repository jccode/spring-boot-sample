package com.github.jccode.springsecuritydemo.guide;

import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;
import com.github.jccode.springsecurity.common.authentication.RESTAuthenticationEntryPoint;
import com.github.jccode.springsecurity.common.authentication.RESTAuthenticationFailureHandler;
import com.github.jccode.springsecurity.common.authentication.RESTAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
                .antMatchers("/app/guest").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .permitAll()
            .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())
                .permitAll()
            .and()
                .addFilterAfter(csrfTokenResponseHeaderBindingFilter(), CsrfFilter.class)  // add csrfToken to response
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()); // authentication failed, return 403
        // @formatter:on
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN").and()
                .withUser("tom").password("cat").roles("USER");
    }


    private CsrfTokenResponseHeaderBindingFilter csrfTokenResponseHeaderBindingFilter() {
        return new CsrfTokenResponseHeaderBindingFilter();
    }

    private HttpStatusReturningLogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }

    private RESTAuthenticationEntryPoint authenticationEntryPoint() {
        return new RESTAuthenticationEntryPoint();
    }

    private RESTAuthenticationFailureHandler authenticationFailureHandler() {
        return new RESTAuthenticationFailureHandler();
    }

    private RESTAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new RESTAuthenticationSuccessHandler();
    }

}
