package com.indiastarzilla.developers.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPublicKey;

@Configuration
@ConfigurationProperties("key")
public class KeyConfig {
    RSAPublicKey location;

    public RSAPublicKey getLocation() {
        return location;
    }

    public void setLocation(RSAPublicKey location) {
        this.location = location;
    }
}
