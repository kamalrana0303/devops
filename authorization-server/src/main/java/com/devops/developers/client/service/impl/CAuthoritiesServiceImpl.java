package com.devops.developers.client.service.impl;

import com.devops.developers.client.dto.CAuthoritiesDto;
import com.devops.developers.client.entity.CAuthorities;
import com.devops.developers.client.repository.CAuthoritiesRepository;
import com.devops.developers.client.service.CAuthoritiesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CAuthoritiesServiceImpl implements CAuthoritiesService {
    @Autowired
    CAuthoritiesRepository cAuthoritiesRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public Optional<CAuthoritiesDto> findCAuthoritiesByName(String name){
        return this.cAuthoritiesRepository.findCAuthoritiesByName(name).map(x->mapper.map(x, CAuthoritiesDto.class));
    }

    @Override
    public Optional<CAuthoritiesDto> save(CAuthoritiesDto value){
        Optional<CAuthoritiesDto> cAuthoritiesDto=findCAuthoritiesByName(value.getName());
        if(cAuthoritiesDto.isEmpty()){
            cAuthoritiesDto=Optional.of(cAuthoritiesRepository.save(mapper.map(value, CAuthorities.class)))
                    .map(x->mapper.map(x,CAuthoritiesDto.class));
        }
        return cAuthoritiesDto;
    }
}
