package com.devops.developers.config.security.permission;

import com.devops.developers.dto.CustomerDto;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.io.Serializable;

public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetObject, Object permission) {
        JwtAuthenticationToken jwt = (JwtAuthenticationToken) authentication;
        String username = authentication.getName() != null ? authentication.getName() : (String) jwt.getToken().getClaims().get("user_name");
        CustomerDto customerDto = (CustomerDto) targetObject;

        boolean havePermission = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(permission));
        boolean isValidUser = customerDto.getUsername().equals(username);
        return isValidUser && havePermission;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
