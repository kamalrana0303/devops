package com.devops.developers.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;

public class ClientInMemoryConfig {
    public void getClientConfig(ClientDetailsServiceConfigurer clients, PasswordEncoder passwordEncoder) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret(passwordEncoder.encode("secret1"))
                .accessTokenValiditySeconds(5000)
                .scopes("read")
                .authorizedGrantTypes("password", "refresh_token")
                .and()
                .withClient("client2")
                .accessTokenValiditySeconds(5000)
                .secret(passwordEncoder.encode("secret2"))
                .scopes("read")
                .authorizedGrantTypes("authorization_code")
                .redirectUris("http://localhost:9090")
                .and()
                .withClient("client3")
                .accessTokenValiditySeconds(5000)
                .secret(passwordEncoder.encode("secret3"))
                .scopes("read")
                .authorizedGrantTypes("client_credentials")
                .and()
                .withClient("resource-server")
                .secret(passwordEncoder.encode("resource-server"))
        ;
    }
}
