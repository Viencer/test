package com.test.config.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@ComponentScan("com.test")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = Logger.getLogger(WebSecurityConfig.class);

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) {
        try {
            http.authorizeRequests().
                    antMatchers("/index", "/").permitAll()
                    .antMatchers("/admin", "/user").authenticated()
                    .and()
                    .formLogin()
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/");
        } catch (Exception e) {
            logger.error("error in configure. WebSecurityConfig.class " + e.getMessage());
        }
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationMgr) {
        try {
            authenticationMgr.jdbcAuthentication().dataSource(dataSource).passwordEncoder(NoOpPasswordEncoder.getInstance())
                    .usersByUsernameQuery(
                            "select USER_NAME, PASSWORD, ENABLED from LAB3_ROLES where USER_NAME = ?")
                    .authoritiesByUsernameQuery(
                            "select USER_NAME, ROLE from LAB3_ROLES where USER_NAME = ?");
        } catch (Exception e) {
            logger.error("error in configure. WebSecurityConfig.class " + e.getMessage());
        }
    }
}