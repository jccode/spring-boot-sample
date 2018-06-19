package com.github.jccode.springsecuritydemo.zuuldemo.authcenter.config;

import com.github.jccode.springsecuritydemo.zuuldemo.common.SecurityCommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Import(SecurityCommonConfig.class)
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
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(SQL_users_by_username)
                .authoritiesByUsernameQuery(SQL_roles_by_username)
                .groupAuthoritiesByUsername(SQL_role_permissions_by_username);
    }

    /**
     * expose `AuthenticationManager` as A Bean.
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}