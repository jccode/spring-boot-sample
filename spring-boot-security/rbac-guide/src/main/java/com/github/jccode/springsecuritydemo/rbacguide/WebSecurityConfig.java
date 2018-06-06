package com.github.jccode.springsecuritydemo.rbacguide;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SQL_users_by_username =
            "select username,password,enabled from user where username = ?";

    /*
     "select u.username, p.NAME from USER u join USER_ROLE ur on ur.USER_ID = u.ID\n" +
            "  join ROLE_PERMISSION up on ur.ROLE_ID = up.ROLE_ID\n" +
            "  join PERMISSION p on up.PERMISSION_ID = p.ID\n" +
            "where u.USERNAME = ?";
    */

    private static final String SQL_permissions_by_username =
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(SQL_users_by_username)
                .authoritiesByUsernameQuery(SQL_permissions_by_username)
                .groupAuthoritiesByUsername(SQL_role_permissions_by_username);
    }

}
