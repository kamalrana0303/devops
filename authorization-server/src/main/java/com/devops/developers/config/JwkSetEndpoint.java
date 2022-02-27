package com.devops.developers.config;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import com.nimbusds.jose.jwk.RSAKey;
@FrameworkEndpoint
public class JwkSetEndpoint {
    KeyPair keyPair;
    public JwkSetEndpoint(KeyPair keyPair){
        this.keyPair= keyPair;
    }
    @GetMapping("/.well-known/jwks.json")
    @ResponseBody
    public Map<String, Object> getKey(Principal principal){
        RSAPublicKey publicKey= (RSAPublicKey) this.keyPair.getPublic();
        RSAKey rsaKey= new RSAKey.Builder(publicKey).build();
        return new JWKSet(rsaKey).toJSONObject();
    }


}
