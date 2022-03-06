package com.devops.developers.client.service.impl;

import com.devops.developers.client.dto.GrantTypeDto;
import com.devops.developers.client.entity.GrantType;
import com.devops.developers.client.repository.GrantTypeRepository;
import com.devops.developers.client.service.GrantTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GrantTypeServiceImpl implements GrantTypeService {
    @Autowired
    GrantTypeRepository grantTypeRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public Optional<GrantType> findGrantTypeByName(String name) {
        return Optional.ofNullable(this.grantTypeRepository.findGrantTypeByName(name));
    }

    @Override
    public Optional<GrantType> save(GrantTypeDto value) {
        return Optional.ofNullable(this.grantTypeRepository.save(mapper.map(value, GrantType.class)));

    }
}
