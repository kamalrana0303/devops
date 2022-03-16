package com.devops.developers.config.security.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

public class CustomAuthenticationConverter extends JwtAuthenticationConverter {
    Converter<Jwt, String> nameConverter= new JwtNameConverter();

    public void setNameConverter(Converter<Jwt, String> nameConverter) {
        this.nameConverter = nameConverter;
    }
}
