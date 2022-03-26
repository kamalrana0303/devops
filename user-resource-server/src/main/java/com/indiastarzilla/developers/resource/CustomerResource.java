package com.indiastarzilla.developers.resource;

import com.indiastarzilla.developers.model.TokenRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
@RequestMapping("auth")
public class CustomerResource {

    private final WebClient webClient;

    public CustomerResource(WebClient webClient) {
        this.webClient = webClient;
    }

    @PostMapping("token")
    public ResponseEntity<TokenRest> getToken(@RegisteredOAuth2AuthorizedClient("client_authorization_code") OAuth2AuthorizedClient tokenClient,
                              @RequestParam("grant_type") String grant_type,
                              @RequestParam("scope") String scope,
                              @RequestParam("code") String code){
        URI uri= URI.create("http://localhost:8080/auth/token?grant_type="+grant_type+"&scope="+scope+"&code="+code);
        UriComponentsBuilder builder= UriComponentsBuilder.fromUri(uri);
        return ResponseEntity.ok().body(getTokenRest(tokenClient,uri.toString()));
    }

    private TokenRest getTokenRest(OAuth2AuthorizedClient tokenClient, String tokenUri){
        ParameterizedTypeReference<TokenRest> typeRef= new ParameterizedTypeReference<TokenRest>() {};
        return this.webClient
                .post()
                .uri(tokenUri)
                .attributes(oauth2AuthorizedClient(tokenClient))
                .retrieve()
                .bodyToMono(typeRef)
                .block();
    }

}
