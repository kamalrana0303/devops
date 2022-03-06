package com.devops.developers.client.service;

import com.devops.developers.client.dto.ScopeDto;
import com.devops.developers.client.entity.Scope;

import java.util.Optional;

public interface ScopeService {
    Optional<Scope> findScopeByName(String name);

    Optional<Scope> save(ScopeDto scopeDto);

}
