package com.devops.developers.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import java.util.Collection;
import java.util.List;

public class WithCustomeSecurityContextFactory implements WithSecurityContextFactory<WithCustomerUser> {
    @Override
    public SecurityContext createSecurityContext(WithCustomerUser annotation) {
        SecurityContext context= SecurityContextHolder.createEmptyContext();
        Jwt jwt = Jwt.withTokenValue("token")
                .headers(x-> {
                    x.put("alg","RS256");
                    x.put("type","JWT");
                })
                .claims(x->{
                    x.put("user_name", "kamal");
                    x.put("authorities", List.of(annotation.authority()));
                })
                .build();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ADMIN");
        JwtAuthenticationToken token = new JwtAuthenticationToken(jwt, authorities, jwt.getClaim("user_name"));

        context.setAuthentication(token);
        return context;
    }
}
