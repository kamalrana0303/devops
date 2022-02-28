package com.indiastarzilla.developers.config;


import com.nimbusds.jose.shaded.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.stream.Collectors;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    RestTemplate rest;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Override
    protected  void configure(HttpSecurity http) throws  Exception{
        http.oauth2ResourceServer(
                c->{
                    c.jwt(t->{
                        t.decoder(jwtDecoder()).jwkSetUri(jwkSetUri);
                    });
                }
        );

        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.POST,"customer/add-user").permitAll()
                .mvcMatchers("/customer/**").hasAuthority("read")
                .anyRequest().authenticated();
    }

    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtAuthenticationConverter conv= new JwtAuthenticationConverter();
        conv.setJwtGrantedAuthoritiesConverter(jwt->{
            JSONArray a= (JSONArray) jwt.getClaims().get("authorities");
            return a.stream()
                    .map(String::valueOf)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });
        return  conv;
    }

    @Bean
    public JwtDecoder jwtDecoder(){

        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).restOperations(rest).build();
    }

}
