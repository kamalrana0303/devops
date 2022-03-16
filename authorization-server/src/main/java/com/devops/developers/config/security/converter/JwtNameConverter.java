package com.devops.developers.config.security.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtNameConverter implements Converter<Jwt, String> {
    public JwtNameConverter(){}
    @Override
    public String convert(Jwt source) {
       return source.getClaim("user_name");
    }
}

