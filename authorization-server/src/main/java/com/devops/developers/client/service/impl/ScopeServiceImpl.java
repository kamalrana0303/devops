package com.devops.developers.client.service.impl;

import com.devops.developers.client.dto.ScopeDto;
import com.devops.developers.client.entity.Scope;
import com.devops.developers.client.repository.ScopeRepository;
import com.devops.developers.client.service.ScopeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScopeServiceImpl implements ScopeService {
    @Autowired
    ScopeRepository scopeRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public Optional<Scope> findScopeByName(String name) {
        return Optional.ofNullable(this.scopeRepository.findScopeByName(name));
    }

    @Override
    public Optional<Scope> save(ScopeDto value) {
        return Optional.ofNullable(this.scopeRepository.save(mapper.map(value, Scope.class)));
    }
}
