package com.devops.developers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("authorization-server")
public class ClientConfiguration {

    Map<String, ClientAttributes> clients;

    public Map<String, ClientAttributes> getClients() {
        return clients;
    }

    public void setClients(Map<String, ClientAttributes> clients) {
        this.clients = clients;
    }
}
