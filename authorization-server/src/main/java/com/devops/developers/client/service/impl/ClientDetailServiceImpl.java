package com.devops.developers.client.service.impl;

import com.devops.developers.client.clientdetail.ClientDetailsImpl;
import com.devops.developers.client.dto.*;
import com.devops.developers.client.entity.Client;
import com.devops.developers.client.entity.GrantType;
import com.devops.developers.client.model.request.ClientDetailsRM;
import com.devops.developers.client.repository.ClientRepository;
import com.devops.developers.client.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ClientDetailServiceImpl implements ClientDetailsService, ClientDetailsRegisteration {

    private final ClientRepository clientRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final CAuthoritiesService cAuthoritiesService;
    private final GrantTypeService grantTypeService;
    private final ScopeService scopeService;
    private final RedirectUriService redirectUriService;

    public ClientDetailServiceImpl(ClientRepository clientRepository, ModelMapper mapper, PasswordEncoder passwordEncoder, CAuthoritiesService cAuthoritiesService, GrantTypeService grantTypeService, ScopeService scopeService, RedirectUriService redirectUriService) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.cAuthoritiesService = cAuthoritiesService;
        this.grantTypeService = grantTypeService;
        this.scopeService = scopeService;
        this.redirectUriService = redirectUriService;
    }

    private ClientDto save(ClientDto value) {
        value.setClientSecret(passwordEncoder.encode(value.getClientSecret()));
        Client savedValue = this.clientRepository.save(mapper.map(value, Client.class));
        return mapper.map(savedValue, ClientDto.class);
    }

    public Optional<Client> findClientByClientId(String clientId) {
        return Optional.ofNullable(this.clientRepository.findClientByClientId(clientId));
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return findClientByClientId(clientId).map(
                c -> {
                    return new ClientDetailsImpl(c);
                }
        ).orElseThrow(() -> new ClientRegistrationException("invalid client"));
    }

    @Override
    public ClientDto addClient(ClientDetailsRM clientDetailsRM) {
        ClientDto clientDto = mapper.map(clientDetailsRM, ClientDto.class);
        return save(clientDto);
    }

    @Override
    public ClientDto deattachScopeFromClient(String clientId, String scopeName) {
        return findClientByClientId(clientId).map(client -> {
            this.scopeService.findScopeByName(scopeName).map(scope -> {
                if (client.getScopes().contains(scope)) {
                    client.getScopes().remove(scope);
                }
                return scope;
            }).orElseThrow(() -> new RuntimeException("invalid scope"));
            this.clientRepository.save(client);
            return mapper.map(client, ClientDto.class);
        }).orElseThrow(() -> new RuntimeException("invalid client"));

    }

    @Override
    public ClientDto deattachAuthoritiesFromClient(String clientId, String cAuthorityName) {
        return findClientByClientId(clientId).map(client -> {
            this.cAuthoritiesService.findCAuthoritiesByName(cAuthorityName).map(authority -> {
                if (client.getcAuthorities().contains(authority)) {
                    client.getcAuthorities().remove(authority);
                }
                return authority;

            }).orElseThrow(() -> new RuntimeException("invalid authority"));
            this.clientRepository.save(client);
            return mapper.map(client, ClientDto.class);
        }).orElseThrow(() -> new RuntimeException("invalid client"));
    }

    @Override
    public ClientDto deattachGrantTypeFromClient(String clientId, String grantTypeName) {
        return findClientByClientId(clientId).map(client -> {
            this.grantTypeService.findGrantTypeByName(grantTypeName).map(grantType -> {
                if (client.getAuthorizedGrantTypes().contains(grantType)) {
                    client.getAuthorizedGrantTypes().remove(grantType);
                }
                return grantType;
            }).orElseThrow(() -> new RuntimeException("invalid grant type"));
            this.clientRepository.save(client);
            return mapper.map(client, ClientDto.class);
        }).orElseThrow(() -> new RuntimeException("invalid cleint"));

    }

    @Override
    public ClientDto deattachRegisteredRedirectUriFromClient(String clientId, String redirectUri) {
        return findClientByClientId(clientId).map(client -> {
            this.redirectUriService.findRedirectUriByName(redirectUri).map(uri -> {
                if (client.getRegisteredRedirectUri().contains(uri)) {
                    client.removeRedirectUri(uri);
                }
                return uri;
            }).orElseThrow(() -> new RuntimeException("invalid redirect uri"));
            this.clientRepository.save(client);
            return mapper.map(client, ClientDto.class);
        }).orElseThrow(() -> new RuntimeException("invalid client"));
    }

    @Override
    public ClientDto attachAuthoritiesToClient(List<CAuthoritiesDto> cAuthoritiesDtos, String clientId) {
        return findClientByClientId(clientId).map(client -> {
            for (CAuthoritiesDto authority : cAuthoritiesDtos) {
                this.cAuthoritiesService.findCAuthoritiesByName(authority.getName()).map(found -> {
                    if (!client.getcAuthorities().contains(found)) {
                        client.getcAuthorities().add(found);
                    }
                    return found;
                }).or(() -> {
                    return this.cAuthoritiesService.save(authority).map(saved -> {
                        client.getcAuthorities().add(saved);
                        return saved;
                    });
                });
            }
            return mapper.map(client, ClientDto.class);
        }).orElseThrow(() -> new RuntimeException("invalid client"));

    }

    @Override
    public ClientDto attachScopeToClient(List<ScopeDto> scopeDtos, String clientId) {

        return findClientByClientId(clientId).map(client -> {
            for (ScopeDto scope : scopeDtos) {
                this.scopeService.findScopeByName(scope.getName()).map(found -> {
                    if (!client.getScopes().contains(found)) {
                        client.getScopes().add(found);
                    }
                    return found;
                }).or(() -> {
                    return this.scopeService.save(scope).map(saved -> {
                        client.getScopes().add(saved);
                        return saved;
                    });
                });
            }
            this.clientRepository.save(client);
            return mapper.map(client, ClientDto.class);
        }).orElseThrow(() -> new RuntimeException("invalid client"));

    }

    @Override
    public ClientDto attachGrantTypeToClient(List<GrantTypeDto> grantTypes, String clientId) {
        return findClientByClientId(clientId).map(client -> {
            for (GrantTypeDto grantType : grantTypes) {
                this.grantTypeService.findGrantTypeByName(grantType.getName()).map(found -> {
                    if (!client.getAuthorizedGrantTypes().contains(found)) {
                        client.getAuthorizedGrantTypes().add(found);
                    }
                    return found;
                }).or(() -> {
                    return this.grantTypeService.save(grantType).map(saved -> {
                        client.getAuthorizedGrantTypes().add(saved);
                        return saved;
                    });
                });
            }
            this.clientRepository.save(client);
            return mapper.map(client, ClientDto.class);
        }).orElseThrow(() -> new RuntimeException("invalid client"));
    }

    @Override
    public ClientDto attachRegisteredRedirectUris(List<RedirectUriDto> redirectUriDtos, String clientId) {

        return findClientByClientId(clientId).map(client -> {
            for (RedirectUriDto uri : redirectUriDtos) {
                this.redirectUriService.findRedirectUriByName(uri.getName()).map(found -> {
                    if (!client.getRegisteredRedirectUri().contains(found)) {
                        client.addRedirectUri(found);
                    }
                    return found;
                }).or(() -> {
                    return this.redirectUriService.save(uri).map(saved -> {
                        client.getRegisteredRedirectUri().add(saved);
                        return saved;
                    });
                });
            }
            this.clientRepository.save(client);
            return mapper.map(client, ClientDto.class);
        }).orElseThrow(() -> new RuntimeException("invalid client"));

    }

    @Override
    @PostAuthorize("returnObject.clientId == #clientId")
    public ClientDto getClientByClientId(String clientId) {
        return findClientByClientId(clientId).map(client -> mapper.map(client, ClientDto.class)).orElseThrow(() -> new RuntimeException("invalid client"));
    }
}
