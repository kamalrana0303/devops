package com.devops.developers.config.security.filter;


import com.devops.developers.config.security.authentication.OtpAuthentication;
import com.devops.developers.config.security.authentication.UsernamePasswordAuthentication;
import com.devops.developers.customer.entity.RoleName;
import com.devops.developers.customer.model.request.CustomerRM;
import com.devops.developers.customer.model.request.LoginRM;
import com.devops.developers.customer.service.CustomerService;
import com.devops.developers.dto.CustomerDto;
import com.devops.developers.dto.RoleDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class UserNamePasswordAuthFilter extends OncePerRequestFilter {


    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    private final ModelMapper mapper;
    private final RestTemplate restTemplate;

    public UserNamePasswordAuthFilter(AuthenticationManager authenticationManager, CustomerService customerService, RestTemplate restTemplate, ModelMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.mapper = mapper;
        this.restTemplate=restTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        if (request.getServletPath().equals("/customer/sign-up")) {
            CustomerRM customerRM = objectMapper.readValue(request.getInputStream(), CustomerRM.class);
            var a = new OtpAuthentication(customerRM.getPhoneNumber(), customerRM.getOtpNo());
            authenticationManager.authenticate(a);
            SecurityContextHolder.getContext().setAuthentication(a);
            CustomerDto customerDto = mapper.map(customerRM, CustomerDto.class);
            customerDto.setRoles(Collections.singleton(new RoleDto(RoleName.USER)));
            this.customerService.createCustomer(customerDto);
        }
        if(request.getServletPath().equals("/login")) {
            LoginRM loginRM= objectMapper.readValue(request.getInputStream(), LoginRM.class);
            var a = new UsernamePasswordAuthentication(loginRM.getUsername(), loginRM.getPassword());
            authenticationManager.authenticate(a);
            SecurityContextHolder.getContext().setAuthentication(a);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        boolean isNotLogin=!request.getServletPath().equals("/login");
        boolean isNotSignUp=!request.getServletPath().equals("/customer/sign-up");
        boolean shouldNotFilter=isNotSignUp ;// && isNotLogin;
        return shouldNotFilter  ;
    }



}
