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
            "select u.username, r.name from user u join user_role ur on ur.user_id = u.id\n" +
            "  join role r on ur.role_id = r.id\n" +
            "where u.username = ?";

    private static final String SQL_role_permissions_by_username =
            "select r.id, r.name, p.name from user u\n" +
            "  join user_role ur on ur.user_id = u.id\n" +
            "  join role r on ur.role_id = r.id\n" +
            "  join role_permission up on ur.role_id = up.role_id\n" +
            "  join permission p on up.permission_id = p.id\n" +
            "where u.username = ?";


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