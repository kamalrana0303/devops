package com.devops.developers.client.service.impl;

import com.devops.developers.client.dto.GrantTypeDto;
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
    public Optional<ScopeDto> findScopeByName(String name){
        return this.scopeRepository.findScopeByName(name).map(x->mapper.map(x, ScopeDto.class));
    }

    @Override
    public Optional<ScopeDto> save(ScopeDto value){
       Optional<ScopeDto> scopeDto=findScopeByName(value.getName());
       if(scopeDto.isEmpty()){
           scopeDto=Optional.of(this.scopeRepository.save(mapper.map(value, Scope.class)))
                   .map(x-> mapper.map(x,ScopeDto.class));
       }
       return scopeDto;
    }
}
