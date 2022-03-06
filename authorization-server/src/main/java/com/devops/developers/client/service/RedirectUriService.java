package com.devops.developers.client.service;

import com.devops.developers.client.dto.RedirectUriDto;
import com.devops.developers.client.entity.RedirectUri;

import java.util.Optional;

public interface RedirectUriService {
    Optional<RedirectUri> save(RedirectUriDto redirectUriDto);

    Optional<RedirectUri> findRedirectUriByName(String name);
}
