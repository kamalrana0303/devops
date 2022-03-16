package com.devops.developers.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("auth")
public class TokenProxyServer {
    @Autowired
    RestTemplate restTemplate;
    @PostMapping("token")
    public ResponseEntity<?> fetchToken(@RequestParam("grant_type") String grant_type
            , @RequestParam("scope") String scope, @RequestParam("code") String code){
        URI uri= URI.create("http://localhost:8080/oauth/token?grant_type="+grant_type+"&scope="+scope+"&code="+code);
        UriComponentsBuilder builder= UriComponentsBuilder.fromUri(uri);
        HttpHeaders headers= new HttpHeaders();
        headers.setBasicAuth("client_authorization_code", "client_authorization_code");
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<Object> obj=this.restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,Object.class);
        return ResponseEntity.ok(obj);
    }

}
