package com.devops.developers.config.security.converter;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.util.Map;

public class CustomAccessTokenConverter implements AccessTokenConverter {
    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        Map<String, Object> map = (Map<String, Object>) this.tokenConverter.convertAccessToken(oAuth2AccessToken, oAuth2Authentication);
        map.put("active", oAuth2AccessToken.getExpiresIn() > 0);
        return map;
    }


    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        return this.tokenConverter.extractAccessToken(value, map);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        return this.tokenConverter.extractAuthentication(map);
    }
}
