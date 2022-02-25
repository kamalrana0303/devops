package com.devops.developers.client.repository;

import com.devops.developers.client.entity.GrantType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GrantTypeRepository extends CrudRepository<GrantType, Long> {
    public Optional<GrantType> findGrantTypeByName(String name);
    public GrantType save(GrantType grantType);
}
