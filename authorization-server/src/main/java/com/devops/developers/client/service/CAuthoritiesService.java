package com.devops.developers.client.service;

import com.devops.developers.client.dto.CAuthoritiesDto;

import java.util.Optional;

public interface CAuthoritiesService {
    Optional<CAuthoritiesDto> findCAuthoritiesByName(String name);

    Optional<CAuthoritiesDto> save(CAuthoritiesDto value);
}
