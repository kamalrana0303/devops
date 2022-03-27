package com.everyday.developer.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Arrays;
import java.util.UUID;

@Configuration
public class ClientConfig {
    @Autowired
    ClientRegistrationConfig properties;
    @Bean
    public ClientRegistrationRepository clientRegisteration(){
        ClientRegistration.Builder builder=this.getBuilder(UUID.randomUUID().toString(), ClientAuthenticationMethod.CLIENT_SECRET_BASIC, properties.getRedirectUri());
        ClientRegistration registration = builder
                .authorizationUri(properties.getAuthorizationUri())
                .clientId(properties.getClientId())
                .clientSecret(properties.getClientSecret())
                .tokenUri(properties.getTokenUri())
                .redirectUri(properties.getRedirectUri())
                .scope(properties.getScopes().split(","))
                .clientName(properties.getClientName())
                .jwkSetUri(properties.getJwkSetUri())
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .userInfoUri(properties.getUserInfoUri())
                .build();
        return new InMemoryClientRegistrationRepository(Arrays.asList(registration));
    }
    protected final ClientRegistration.Builder getBuilder(String registrationId, ClientAuthenticationMethod method, String redirectUri) {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUri(redirectUri);
        return builder;
    }
}
