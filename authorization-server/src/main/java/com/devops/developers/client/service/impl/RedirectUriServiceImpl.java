package com.devops.developers.client.service.impl;

import com.devops.developers.client.dto.RedirectUriDto;
import com.devops.developers.client.entity.RedirectUri;
import com.devops.developers.client.repository.RedirectUriRepository;
import com.devops.developers.client.service.RedirectUriService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedirectUriServiceImpl implements RedirectUriService {
    private final RedirectUriRepository redirectUriRepository;
    private final ModelMapper mapper;

    public RedirectUriServiceImpl(RedirectUriRepository redirectUriRepository, ModelMapper mapper) {
        this.redirectUriRepository = redirectUriRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<RedirectUri> save(RedirectUriDto redirectUriDto) {
        return Optional.ofNullable(this.redirectUriRepository.save(mapper.map(redirectUriDto, RedirectUri.class)));
    }

    @Override
    public Optional<RedirectUri> findRedirectUriByName(String name) {
        return Optional.ofNullable(this.redirectUriRepository.findRedirectUriByName(name));
    }

}
