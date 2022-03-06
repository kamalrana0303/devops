package com.indiastarzilla.developers.resource;

import com.indiastarzilla.developers.ui.model.request.OtpRM;
import com.indiastarzilla.developers.ui.model.response.OtpRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("otp")
public class OtpResource {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping
    ResponseEntity<?> requestOtp(@RequestBody OtpRM otpRM, @Value("${application.authorization-server.baseurl}") String baseUrl) {
        URI uri = URI.create(baseUrl);
        UriComponentsBuilder path = UriComponentsBuilder.fromUri(uri).path("otp/request");
        HttpEntity<OtpRM> entity = new HttpEntity<>(otpRM);
        ResponseEntity<OtpRest> response = this.restTemplate.exchange(path.toUriString(), HttpMethod.POST, entity, OtpRest.class);
        return response;
    }
}
