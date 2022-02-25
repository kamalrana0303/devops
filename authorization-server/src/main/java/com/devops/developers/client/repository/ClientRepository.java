package com.devops.developers.client.repository;

import com.devops.developers.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findClientByClientId(String clientId);
    Client save(Client client);
}
