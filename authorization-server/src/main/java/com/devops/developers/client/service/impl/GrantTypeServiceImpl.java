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
    public Optional<GrantTypeDto> findGrantTypeByName(String name){
        return this.grantTypeRepository.findGrantTypeByName(name).map(x-> mapper.map(x, GrantTypeDto.class));
    }

    @Override
    public Optional<GrantTypeDto> save(GrantTypeDto value){
        Optional<GrantTypeDto> grantType=findGrantTypeByName(value.getName());
        if(grantType.isEmpty()){
            grantType=Optional.of(this.grantTypeRepository.save(mapper.map(value, GrantType.class)))
                    .map(x-> mapper.map(x,GrantTypeDto.class));
        }
        return grantType;
    }
}
