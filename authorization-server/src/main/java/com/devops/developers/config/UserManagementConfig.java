package com.devops.developers.config;


import com.devops.developers.config.security.converter.CustomAuthenticationConverter;
import com.devops.developers.config.security.filter.UserNamePasswordAuthFilter;
import com.devops.developers.config.security.provider.OtpAuthenticationProvider;
import com.devops.developers.config.security.provider.UsernamePasswordAuthProvider;
import com.devops.developers.customer.service.CustomerService;
import com.nimbusds.jose.shaded.json.JSONArray;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;
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
        http
                .addFilterAt(new UserNamePasswordAuthFilter(authenticationManager(), customerService, rest, mapper), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .mvcMatchers(HttpMethod.POST, "/otp/request").permitAll()
                .mvcMatchers("/auth/token").permitAll()
                .anyRequest()
                .authenticated();

        http.oauth2ResourceServer(c -> {
            c.jwt(t -> {
                t.decoder(jwtDecoder()).jwtAuthenticationConverter(jwtAuthenticationConverter()).jwkSetUri(jwkSetUri);
            });
        });
    }

    @Bean
    public CorsConfigurationSource corsConfiguration(){
        CorsConfiguration config= new CorsConfiguration();
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter conv = new JwtAuthenticationConverter();
        conv.setJwtGrantedAuthoritiesConverter(jwt -> {
            JSONArray a = (JSONArray) jwt.getClaims().get("authorities");

            return a.stream()
                    .map(String::valueOf)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });
        conv.setPrincipalClaimName("user_name");
        return conv;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).restOperations(rest).build();
    }

}
