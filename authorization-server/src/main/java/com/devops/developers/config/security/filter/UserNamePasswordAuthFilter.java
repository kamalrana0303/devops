package com.devops.developers.config.security.filter;

import com.devops.developers.config.security.authentication.OtpAuthentication;
import com.devops.developers.config.security.authentication.UsernamePasswordAuthentication;
import com.devops.developers.customer.entity.RoleName;
import com.devops.developers.customer.model.request.CustomerRM;
import com.devops.developers.customer.service.CustomerDetailsServiceImpl;
import com.devops.developers.customer.service.CustomerService;
import com.devops.developers.dto.CustomerDto;
import com.devops.developers.dto.RoleDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class UserNamePasswordAuthFilter  extends OncePerRequestFilter {



    private AuthenticationManager authenticationManager;
    private CustomerService customerService;
    private ModelMapper mapper;

    public UserNamePasswordAuthFilter(AuthenticationManager authenticationManager, CustomerService customerService, ModelMapper mapper){
        this.authenticationManager= authenticationManager;
        this.customerService=customerService;
        this.mapper=mapper;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        CustomerRM customerRM=objectMapper.readValue(request.getInputStream(), CustomerRM.class);
        if(request.getServletPath().equals("/customer/sign-up")){
            var a= new OtpAuthentication(customerRM.getPhoneNumber(), customerRM.getOtpNo());
            authenticationManager.authenticate(a);
            SecurityContextHolder.getContext().setAuthentication(a);
            CustomerDto customerDto=mapper.map(customerRM, CustomerDto.class);
            customerDto.setRoles(Collections.singleton(new RoleDto(RoleName.USER)));
            this.customerService.createCustomer(customerDto);
        }
        else{
            var a= new UsernamePasswordAuthentication(customerRM.getUsername(), customerRM.getPassword());
            authenticationManager.authenticate(a);
            SecurityContextHolder.getContext().setAuthentication(a);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/customer/sign-up");
    }

}
