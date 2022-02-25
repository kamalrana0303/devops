package com.devops.developers.client.model.response;

import com.devops.developers.client.model.request.*;

import java.util.Set;

public class ClientDetailRest {
    private String clientId;
    private boolean secretRequired=true;
    private String clientSecret;
    private boolean scoped=true;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private boolean autoApprove=false;
    private Set<ResourceRest> resourceIds;
    private Set<ScopeRest> scopes;
    private Set<GrantTypeRest> authorizedGrantTypes;
    private Set<RedirectUriRest> registeredRedirectUri;
    private Set<CAuthoritiesRest> cAuthorities;

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

    public Set<ResourceRest> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<ResourceRest> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Set<ScopeRest> getScopes() {
        return scopes;
    }

    public void setScopes(Set<ScopeRest> scopes) {
        this.scopes = scopes;
    }

    public Set<GrantTypeRest> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<GrantTypeRest> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public Set<RedirectUriRest> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    public void setRegisteredRedirectUri(Set<RedirectUriRest> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public Set<CAuthoritiesRest> getcAuthorities() {
        return cAuthorities;
    }

    public void setcAuthorities(Set<CAuthoritiesRest> cAuthorities) {
        this.cAuthorities = cAuthorities;
    }
}
