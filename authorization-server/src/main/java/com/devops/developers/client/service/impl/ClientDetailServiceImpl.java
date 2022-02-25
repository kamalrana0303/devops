package com.devops.developers.client.service.impl;

import com.devops.developers.client.dto.CAuthoritiesDto;
import com.devops.developers.client.dto.ClientDto;
import com.devops.developers.client.dto.GrantTypeDto;
import com.devops.developers.client.dto.ScopeDto;
import com.devops.developers.client.entity.CAuthorities;
import com.devops.developers.client.entity.Client;
import com.devops.developers.client.entity.GrantType;
import com.devops.developers.client.model.request.CAuthoritiesRM;
import com.devops.developers.client.model.request.ClientDetailsRM;
import com.devops.developers.client.model.request.GrantTypeRM;
import com.devops.developers.client.model.request.ScopeRM;
import com.devops.developers.client.repository.CAuthoritiesRepository;
import com.devops.developers.client.repository.ClientRepository;
import com.devops.developers.client.repository.GrantTypeRepository;
import com.devops.developers.client.repository.ScopeRepository;
import com.devops.developers.client.service.CAuthoritiesService;
import com.devops.developers.client.service.ClientDetailsRegisteration;
import com.devops.developers.client.service.GrantTypeService;
import com.devops.developers.client.service.ScopeService;
import com.devops.developers.client.wrapper.ClientDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Service
public class ClientDetailServiceImpl implements ClientDetailsService , ClientDetailsRegisteration {

    private final ClientRepository clientRepository;

    private final ModelMapper mapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    ScopeService scopeService;
    @Autowired
    CAuthoritiesService cAuthoritiesService;
    @Autowired
    GrantTypeService grantTypeService;

    public ClientDetailServiceImpl(ClientRepository clientRepository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
        this.passwordEncoder= passwordEncoder;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<Client> client=this.clientRepository.findClientByClientId(clientId);
        return client.map(c-> new ClientDetailsImpl(c))
                .orElseThrow(()-> new ClientRegistrationException("invalid client"));
    }

    public Optional<ClientDto> findClientByClientId(String clientId){
        return this.clientRepository.findClientByClientId(clientId)
                .map(client -> { return mapper.map(client,ClientDto.class);});
    }

    public Optional<ClientDto> save(ClientDto value){
        Optional<ClientDto> clientDto=findClientByClientId(value.getClientId());
        if(clientDto.isEmpty()){
            clientDto= Optional.of(this.clientRepository.save(mapper.map(value,Client.class)))
                    .map(x -> {return mapper.map(x,ClientDto.class);});
        }
        return clientDto;
    }


    @Override
    @Transactional
    public ClientDetails clientRegistertaion(ClientDetailsRM clienDetailsRM) {
        Optional<Client> client=this.clientRepository.findClientByClientId(clienDetailsRM.getClientId());
        if(client.isEmpty()){
            ClientDto clientDto=mapper.map(clienDetailsRM,ClientDto.class);
            clientDto.setClientSecret(passwordEncoder.encode(clientDto.getClientSecret()));
            clientDto.setcAuthorities(new HashSet<>());
            for(CAuthoritiesRM cAuthoritiesRM:clienDetailsRM.getcAuthorities()){
                Optional<CAuthoritiesDto> cAuthoritiesDto=this.cAuthoritiesService.save(mapper.map(cAuthoritiesRM, CAuthoritiesDto.class));
                cAuthoritiesDto.ifPresent(cAuthoritiesDto1 -> {clientDto.getcAuthorities().add(cAuthoritiesDto1);});
            }
            clientDto.setAuthorizedGrantTypes(new HashSet<>());
            for(GrantTypeRM grantTypeRM:clienDetailsRM.getAuthorizedGrantTypes()){
                Optional<GrantTypeDto> grantTypeDto=this.grantTypeService.save(mapper.map(grantTypeRM, GrantTypeDto.class));
                grantTypeDto.ifPresent(grantTypeDto1 -> {clientDto.getAuthorizedGrantTypes().add(grantTypeDto1);});
            }
            clientDto.setScopes(new HashSet<>());
            for(ScopeRM scopeRM:clienDetailsRM.getScopes()){
                Optional<ScopeDto> scopeDto=this.scopeService.save(mapper.map(scopeRM, ScopeDto.class));
                scopeDto.ifPresent(scopeDto1 -> {clientDto.getScopes().add(scopeDto1);});
            }
            client=save(clientDto).map(clientDto1 -> {return mapper.map(clientDto1, Client.class);});
        }

        return client.map(c-> new ClientDetailsImpl(c))
                .orElseThrow(()-> new ClientRegistrationException("invalid client"));
    }
}
