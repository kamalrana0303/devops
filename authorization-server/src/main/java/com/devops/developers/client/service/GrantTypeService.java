package com.devops.developers.client.service;

import com.devops.developers.client.dto.GrantTypeDto;

import java.util.Optional;

public interface GrantTypeService {
    Optional<GrantTypeDto> findGrantTypeByName(String name);

    Optional<GrantTypeDto> save(GrantTypeDto value);
}
