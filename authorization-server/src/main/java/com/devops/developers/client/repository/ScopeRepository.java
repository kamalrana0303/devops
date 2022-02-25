package com.devops.developers.client.repository;

import com.devops.developers.client.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScopeRepository extends CrudRepository<Scope, Long> {
    Optional<Scope> findScopeByName(String name);
    Scope save(Scope scope);
}
