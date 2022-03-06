package com.devops.developers.client.service;

import com.devops.developers.client.dto.GrantTypeDto;
import com.devops.developers.client.entity.GrantType;

import java.util.Optional;

public interface GrantTypeService {
    Optional<GrantType> findGrantTypeByName(String name);

    Optional<GrantType> save(GrantTypeDto value);
}
