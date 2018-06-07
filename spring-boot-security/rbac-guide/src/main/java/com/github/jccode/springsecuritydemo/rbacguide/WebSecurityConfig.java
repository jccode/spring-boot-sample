package com.github.jccode.springsecuritydemo.rbacguide;

import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;
import com.github.jccode.springsecurity.common.authentication.RESTAuthenticationEntryPoint;
import com.github.jccode.springsecurity.common.authentication.RESTAuthenticationFailureHandler;
import com.github.jccode.springsecurity.common.authentication.RESTAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SQL_users_by_username =
            "select username,password,enabled from user where username = ?";

    private static final String SQL_roles_by_username =
            "select u.username, r.NAME from USER u join USER_ROLE ur on ur.USER_ID = u.ID\n" +
            "  join ROLE r on ur.ROLE_ID = r.ID\n" +
            "where u.USERNAME = ?";

    private static final String SQL_role_permissions_by_username =
            "select r.id, r.NAME, p.NAME from USER u\n" +
            "  join USER_ROLE ur on ur.USER_ID = u.ID\n" +
            "  join ROLE r on ur.ROLE_ID = r.id\n" +
            "  join ROLE_PERMISSION up on ur.ROLE_ID = up.ROLE_ID\n" +
            "  join PERMISSION p on up.PERMISSION_ID = p.ID\n" +
            "where u.USERNAME = ?";


    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(SQL_users_by_username)
                .authoritiesByUsernameQuery(SQL_roles_by_username)
                .groupAuthoritiesByUsername(SQL_role_permissions_by_username);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers("/", "/guest", "/me").permitAll()
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
                .httpBasic()
        ;
        // @formatter:on
    }
}
