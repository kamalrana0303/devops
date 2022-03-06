package com.devops.developers.client.service;

import com.devops.developers.client.dto.CAuthoritiesDto;
import com.devops.developers.client.entity.CAuthorities;

import java.util.Optional;

public interface CAuthoritiesService {
    Optional<CAuthorities> findCAuthoritiesByName(String name);

    Optional<CAuthorities> save(CAuthoritiesDto value);
}
