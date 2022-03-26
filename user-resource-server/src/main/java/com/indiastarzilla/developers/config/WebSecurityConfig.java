package com.indiastarzilla.developers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login();
        http.csrf().disable();
        http.cors(c->{
            CorsConfigurationSource cs= r->{
                CorsConfiguration cc=new CorsConfiguration();
                cc.setAllowedOrigins(List.of("http://localhost:4200"));
                cc.setAllowedHeaders(List.of("Request","Authorization"));
                cc.setAllowedMethods(List.of("REQUEST","POST", "GET"));
                return cc;
            };
            c.configurationSource(cs);
        });
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }


//    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
//    String jwkSetUri;

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
//        return http.authorizeExchange()
//                .anyExchange().authenticated()
//                .and().oauth2ResourceServer()
//                .jwt(
//                        jwtSpec -> {
//                            jwtSpec.jwkSetUri(jwkSetUri);
//                        }
//                ).and().build();
//    }

}
