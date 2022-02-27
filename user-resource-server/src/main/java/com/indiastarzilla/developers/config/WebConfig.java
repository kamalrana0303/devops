package com.indiastarzilla.developers.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class WebConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        RestTemplate rest = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(60))
                .setReadTimeout(Duration.ofSeconds(60))
                .build();
        return rest;
    }


}
