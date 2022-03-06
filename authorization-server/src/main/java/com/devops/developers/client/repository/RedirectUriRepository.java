package com.devops.developers.client.repository;

import com.devops.developers.client.entity.RedirectUri;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RedirectUriRepository extends JpaRepository<RedirectUri, Long> {
    RedirectUri save(RedirectUri redirectUri);

    RedirectUri findRedirectUriByName(String name);
}
