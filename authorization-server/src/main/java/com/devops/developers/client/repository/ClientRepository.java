package com.devops.developers.client.repository;

import com.devops.developers.client.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findClientByClientId(String clientId);

    Client save(Client client);
}
