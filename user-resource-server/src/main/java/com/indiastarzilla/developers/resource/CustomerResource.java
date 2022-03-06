package com.indiastarzilla.developers.resource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import com.indiastarzilla.developers.ui.model.response.*;
import com.indiastarzilla.developers.ui.model.request.*;

import java.net.URI;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
@RequestMapping("customer")
public class CustomerResource {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient webClient;

    @PostMapping("sign-up")
    public ResponseEntity<CustomerRest> userSignUp(@RequestBody CustomerRM customerRM, @Value("${application.authorization-server.baseurl}") String baseUrl) {
        URI baseurl = URI.create(baseUrl);

        UriComponentsBuilder uri = UriComponentsBuilder.fromUri(baseurl).path("customer/sign-up");
        HttpEntity<CustomerRM> entity = new HttpEntity<>(customerRM);
        ResponseEntity<CustomerRest> response = this.restTemplate.exchange(uri.toUriString(), HttpMethod.POST, entity, CustomerRest.class);
        return response;
    }

    @PostMapping("{username}")
    public ResponseEntity<?> getCustomerByName(@RegisteredOAuth2AuthorizedClient("login-client") OAuth2AuthorizedClient client,
                                               @PathVariable("username") String username, @Value("${application.authorization-server.baseurl}") String baseUrl) {
        CustomerRest customerRest = getMessages(client, baseUrl + "/customer");
        return ResponseEntity.ok(customerRest);
    }

    private CustomerRest getMessages(OAuth2AuthorizedClient client, String baseUrl) {
        // ParameterizedTypeReference<List<?>> typeRef = new ParameterizedTypeReference<List<Message>>() {};
        return this.webClient
                .post()
                .uri(baseUrl)
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(CustomerRest.class)
                .block();
    }


}
