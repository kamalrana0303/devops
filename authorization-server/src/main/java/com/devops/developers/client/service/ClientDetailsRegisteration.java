package com.devops.developers.client.service;

import com.devops.developers.client.dto.*;
import com.devops.developers.client.model.request.ClientDetailsRM;

import java.util.List;
import java.util.Optional;

public interface ClientDetailsRegisteration {

    ClientDto addClient(ClientDetailsRM clientDetailsRM);

    ClientDto deattachScopeFromClient(String clientId, String scopeName);

    ClientDto deattachAuthoritiesFromClient(String clientId, String cAuthorityName);

    ClientDto deattachGrantTypeFromClient(String clientId, String grantTypeName);

    ClientDto deattachRegisteredRedirectUriFromClient(String clientId, String redirectUri);

    ClientDto attachAuthoritiesToClient(List<CAuthoritiesDto> cAuthoritiesDtos, String clientId);

    ClientDto attachScopeToClient(List<ScopeDto> scopeDtos, String clientId);

    ClientDto attachGrantTypeToClient(List<GrantTypeDto> grantTypes, String clientId);

    ClientDto attachRegisteredRedirectUris(List<RedirectUriDto> redirectUriDtos, String clientId);

    ClientDto getClientByClientId(String clientId);
}
