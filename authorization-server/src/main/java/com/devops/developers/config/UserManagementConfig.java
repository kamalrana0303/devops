package com.devops.developers.config;

import com.devops.developers.config.security.filter.UserNamePasswordAuthFilter;
import com.devops.developers.config.security.provider.OtpAuthenticationProvider;
import com.devops.developers.config.security.provider.UsernamePasswordAuthProvider;
import com.devops.developers.customer.service.CustomerDetailsServiceImpl;
import com.devops.developers.customer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class UserManagementConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private  final UsernamePasswordAuthProvider usernamePasswordAuthProvider;
    private final OtpAuthenticationProvider otpAuthenticationProvider;
    private  final CustomerService customerService;
    private final ModelMapper mapper;
    public UserManagementConfig(UserDetailsService userDetailsService, CustomerService customerService, UsernamePasswordAuthProvider usernamePasswordAuthProvider, OtpAuthenticationProvider otpAuthenticationProvider, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.usernamePasswordAuthProvider= usernamePasswordAuthProvider;
        this.otpAuthenticationProvider= otpAuthenticationProvider;
        this.customerService= customerService;
        this.mapper=mapper;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
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
                .addFilterAt(new UserNamePasswordAuthFilter(authenticationManager(), customerService,mapper), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .authorizeHttpRequests()
                .mvcMatchers(HttpMethod.POST,"/otp/request").permitAll()
                .anyRequest()
                .authenticated();
    }
}
