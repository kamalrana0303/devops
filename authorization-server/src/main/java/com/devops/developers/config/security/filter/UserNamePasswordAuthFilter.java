package com.devops.developers.config.security.filter;

import com.devops.developers.config.security.authentication.OtpAuthentication;
import com.devops.developers.config.security.authentication.UsernamePasswordAuthentication;
import com.devops.developers.customer.model.request.CustomerRM;
import org.codehaus.jackson.map.ObjectMapper;
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


public class UserNamePasswordAuthFilter  extends OncePerRequestFilter {


    private AuthenticationManager authenticationManager;

    public UserNamePasswordAuthFilter(AuthenticationManager authenticationManager){
        this.authenticationManager= authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        CustomerRM customerRM=objectMapper.readValue(request.getInputStream(), CustomerRM.class);
        if(request.getServletPath().equals("/customer/sign-up")){
            var a= new OtpAuthentication(customerRM.getPhoneNumber(), customerRM.getOtpNo());
            authenticationManager.authenticate(a);
            SecurityContextHolder.getContext().setAuthentication(a);
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
