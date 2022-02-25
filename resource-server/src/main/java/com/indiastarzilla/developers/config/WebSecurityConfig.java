package com.indiastarzilla.developers.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.servlet.oauth2.resourceserver.JwtDsl;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-uri}")
    String introspectionUri;
    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-id}")
    String clientId;
    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-secret}")
    String clientSecret;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Value("${key}")
    RSAPublicKey key;

    @Override
    protected  void configure(HttpSecurity http) throws  Exception{
        http.oauth2ResourceServer(
                c->{
                    c.jwt(t->{
                        t.decoder(jwtDecoder());
                    });
                }
        );
        http.authorizeHttpRequests()
                .mvcMatchers("/customer/**").hasAuthority("SCOPE_read")
                .anyRequest()
                .authenticated();
    }

    @Bean
    public JwtDecoder jwtDecoder(){
       // return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();

        return NimbusJwtDecoder.withPublicKey(key).build();
    }

}
