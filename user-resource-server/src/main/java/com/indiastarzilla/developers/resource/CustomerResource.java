package com.indiastarzilla.developers.resource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.indiastarzilla.developers.ui.model.response.*;
import com.indiastarzilla.developers.ui.model.request.*;
import java.net.URI;

@RestController
@RequestMapping("customer")
public class CustomerResource {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("sign-up")
    public ResponseEntity<CustomerRest> hello(@RequestBody CustomerRM customerRM , @Value("${application.authorization-server.baseurl}") String baseUrl){
        URI baseurl = URI.create(baseUrl);
        UriComponentsBuilder uri= UriComponentsBuilder.fromUri(baseurl).path("customer/sign-up");
        HttpEntity<CustomerRM> entity= new HttpEntity<>(customerRM);
        ResponseEntity<CustomerRest> response=this.restTemplate.exchange(uri.toUriString(),HttpMethod.POST,entity,CustomerRest.class);
        return response;
    }

}
