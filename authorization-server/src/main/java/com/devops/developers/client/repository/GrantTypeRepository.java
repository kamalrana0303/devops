package com.devops.developers.client.repository;

import com.devops.developers.client.entity.GrantType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GrantTypeRepository extends CrudRepository<GrantType, Long> {
    GrantType findGrantTypeByName(String name);

    GrantType save(GrantType grantType);
}
