package com.devops.developers.client.dto;

import com.devops.developers.client.entity.*;

import java.util.Set;

public class ClientDto {
    private Long id;
    private String clientId;
    private boolean secretRequired=true;
    private String clientSecret;
    private boolean scoped=true;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private boolean autoApprove=false;
    private Set<ResourceDto> resourceIds;
    private Set<ScopeDto> scopes;
    private Set<GrantTypeDto> authorizedGrantTypes;
    private Set<RedirectUriDto> registeredRedirectUri;
    private Set<CAuthoritiesDto> cAuthorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isSecretRequired() {
        return secretRequired;
    }

    public void setSecretRequired(boolean secretRequired) {
        this.secretRequired = secretRequired;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public boolean isScoped() {
        return scoped;
    }

    public void setScoped(boolean scoped) {
        this.scoped = scoped;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public boolean isAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    public Set<ResourceDto> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<ResourceDto> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Set<ScopeDto> getScopes() {
        return scopes;
    }

    public void setScopes(Set<ScopeDto> scopes) {
        this.scopes = scopes;
    }

    public Set<GrantTypeDto> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<GrantTypeDto> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public Set<RedirectUriDto> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    public void setRegisteredRedirectUri(Set<RedirectUriDto> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public Set<CAuthoritiesDto> getcAuthorities() {
        return cAuthorities;
    }

    public void setcAuthorities(Set<CAuthoritiesDto> cAuthorities) {
        this.cAuthorities = cAuthorities;
    }
}
