package com.devops.developers.config;

import org.bouncycastle.jcajce.provider.asymmetric.RSA;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ClientDetailsService clientDetailServiceImpl;

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(converter());
        //return new JdbcTokenStore(dataSource);
    }

    @Bean
    public JwtAccessTokenConverter converter(){
        var v= new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory= new KeyStoreKeyFactory(
                new ClassPathResource("ssia.jks"),
                "ssia123".toCharArray()
        );
        v.setKeyPair(keyStoreKeyFactory.getKeyPair("ssia"));
        return v;
    }

//    public AccessTokenConverter converter(){
//        return new CustomAccessTokenConverter();
//    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailServiceImpl);
//        clients.inMemory()
//                .withClient("client1")
//                .secret(passwordEncoder.encode("secret1"))
//                .accessTokenValiditySeconds(5000)
//                .scopes("read")
//                .authorizedGrantTypes("password","refresh_token")
//                .and()
//                .withClient("client2")
//                .accessTokenValiditySeconds(5000)
//                .secret(passwordEncoder.encode("secret2"))
//                .scopes("read")
//                .authorizedGrantTypes("authorization_code")
//                .redirectUris("http://localhost:9090")
//                .and()
//                .withClient("client3")
//                .accessTokenValiditySeconds(5000)
//                .secret(passwordEncoder.encode("secret3"))
//                .scopes("read")
//                .authorizedGrantTypes("client_credentials")
//                .and()
//                .withClient("resource-server")
//                .secret(passwordEncoder.encode("resource-server"))
//        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore())
                .accessTokenConverter(converter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) throws Exception{

        securityConfigurer
                .passwordEncoder(passwordEncoder)
                .checkTokenAccess("isAuthenticated()")
                .tokenKeyAccess("permitAll()"); // permitAll() isAuthenticated()

    }

    @Bean
    public ResourceServerTokenServices createResourceServerTokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        tokenServices.setClientId("client1");
        tokenServices.setClientSecret("secret1");
        return tokenServices;
    }
}
