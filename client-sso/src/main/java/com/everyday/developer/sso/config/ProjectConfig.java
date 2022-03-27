package com.everyday.developer.sso.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http.formLogin().disable();
        http.oauth2Login()
                .redirectionEndpoint()
                .baseUri("/login/oauth2/code/*")
                .and()
                .loginPage("/login/oauth2")
                        .authorizationEndpoint()
                                .baseUri("/login/oauth2/authorization");
        http.authorizeRequests().anyRequest().authenticated();
    }
}
