package com.devops.developers.config;


import com.devops.developers.config.security.filter.UserNamePasswordAuthFilter;
import com.devops.developers.config.security.provider.OtpAuthenticationProvider;
import com.devops.developers.config.security.provider.UsernamePasswordAuthProvider;
import com.devops.developers.customer.service.CustomerService;
import com.nimbusds.jose.shaded.json.JSONArray;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Configuration
public class UserManagementConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UsernamePasswordAuthProvider usernamePasswordAuthProvider;
    private final OtpAuthenticationProvider otpAuthenticationProvider;
    private final CustomerService customerService;
    private final ModelMapper mapper;
    private final RestTemplate rest;
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    public UserManagementConfig(UserDetailsService userDetailsService, CustomerService customerService, UsernamePasswordAuthProvider usernamePasswordAuthProvider, OtpAuthenticationProvider otpAuthenticationProvider, PasswordEncoder passwordEncoder, RestTemplate rest, ModelMapper mapper) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.usernamePasswordAuthProvider = usernamePasswordAuthProvider;
        this.otpAuthenticationProvider = otpAuthenticationProvider;
        this.customerService = customerService;
        this.rest = rest;
        this.mapper = mapper;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .authenticationProvider(otpAuthenticationProvider)
                .authenticationProvider(usernamePasswordAuthProvider)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http
                .addFilterAt(new UserNamePasswordAuthFilter(authenticationManager(), customerService, mapper), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .authorizeHttpRequests()
                .mvcMatchers(HttpMethod.POST, "/otp/request").permitAll()
                .mvcMatchers(HttpMethod.GET, "/customer/**").permitAll()
                .anyRequest()
                .authenticated();

        http.oauth2ResourceServer(c -> {
            c.jwt(t -> {
                t.decoder(jwtDecoder()).jwtAuthenticationConverter(jwtAuthenticationConverter()).jwkSetUri(jwkSetUri);
            });
        });

    }

    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter conv = new JwtAuthenticationConverter();
        conv.setJwtGrantedAuthoritiesConverter(jwt -> {
            JSONArray a = (JSONArray) jwt.getClaims().get("authorities");
            return a.stream()
                    .map(String::valueOf)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });
        return conv;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).restOperations(rest).build();
    }

}
