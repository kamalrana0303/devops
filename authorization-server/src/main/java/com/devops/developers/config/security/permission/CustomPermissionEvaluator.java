package com.devops.developers.config.security.permission;

import com.devops.developers.customer.entity.Customer;
import com.devops.developers.dto.CustomerDto;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.io.Serializable;

public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetObject, Object permission) {
        JwtAuthenticationToken jwt = (JwtAuthenticationToken) authentication;
        boolean isValidUser=authentication.getName().equals(((CustomerDto) targetObject).getUsername());
        boolean havePermission = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(permission));
        return isValidUser && havePermission;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
