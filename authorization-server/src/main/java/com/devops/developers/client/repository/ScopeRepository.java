package com.devops.developers.client.repository;

import com.devops.developers.client.entity.Scope;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScopeRepository extends CrudRepository<Scope, Long> {
    Scope findScopeByName(String name);

    Scope save(Scope scope);
}
