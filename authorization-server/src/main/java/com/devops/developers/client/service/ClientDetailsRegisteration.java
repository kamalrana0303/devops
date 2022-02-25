package com.devops.developers.client.service;

import com.devops.developers.client.model.request.ClientDetailsRM;
import org.springframework.security.oauth2.provider.ClientDetails;

public interface ClientDetailsRegisteration {
    public ClientDetails clientRegistertaion(ClientDetailsRM clienDetailsRM);
}
