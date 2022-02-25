package com.devops.developers.client.repository;

import com.devops.developers.client.entity.CAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CAuthoritiesRepository extends CrudRepository<CAuthorities, Long> {
    public Optional<CAuthorities> findCAuthoritiesByName(String name);
    public CAuthorities save(CAuthorities cAuthorities);
}
