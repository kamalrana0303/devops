package com.indiastarzilla.developers.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class WebConfig {
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        var c= clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }

    private ClientRegistration clientRegistration(){
        ClientRegistration cr = ClientRegistration.withRegistrationId("client_authorization_code")
                .clientId("client_authorization_code")
                .clientSecret("client_authorization_code")
                .scope(new String[]{"read"})
                .authorizationUri("http://localhost:8080/oauth/authorize")
                .tokenUri("https://localhost:8080/oauth/token")
                .clientName("client_authorization_code")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
               .redirectUri("http://localhost:4200")
                .build();
        return cr;
    }

}
