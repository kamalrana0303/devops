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
    public Optional<CAuthorities> findCAuthoritiesByName(String name) {
        return Optional.ofNullable(this.cAuthoritiesRepository.findCAuthoritiesByName(name));
    }

    @Override
    public Optional<CAuthorities> save(CAuthoritiesDto value) {
        return Optional.ofNullable(this.cAuthoritiesRepository.save(mapper.map(value, CAuthorities.class)));
    }

}
