package com.devops.developers.client.service;

import com.devops.developers.client.dto.ScopeDto;
import com.devops.developers.client.entity.Scope;

import java.util.Optional;

public interface ScopeService {
    public Optional<ScopeDto> findScopeByName(String name);
    public Optional<ScopeDto> save(ScopeDto scopeDto);
}
