package com.devops.developers.client.repository;

import com.devops.developers.client.entity.CAuthorities;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CAuthoritiesRepository extends CrudRepository<CAuthorities, Long> {
    CAuthorities findCAuthoritiesByName(String name);

    CAuthorities save(CAuthorities cAuthorities);
}
