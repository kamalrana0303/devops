package com.devops.developers.client.model.request;

import com.devops.developers.client.dto.*;

import java.util.Set;

public class ClientDetailsRM {
    private String clientId;
    private boolean secretRequired=true;
    private String clientSecret;
    private boolean scoped=true;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private boolean autoApprove=false;
    private Set<ResourceRM> resourceIds;
    private Set<ScopeRM> scopes;
    private Set<GrantTypeRM> authorizedGrantTypes;
    private Set<RedirectUriRM> registeredRedirectUri;
    private Set<CAuthoritiesRM> cAuthorities;

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

    public Set<ResourceRM> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<ResourceRM> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Set<ScopeRM> getScopes() {
        return scopes;
    }

    public void setScopes(Set<ScopeRM> scopes) {
        this.scopes = scopes;
    }

    public Set<GrantTypeRM> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<GrantTypeRM> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public Set<RedirectUriRM> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    public void setRegisteredRedirectUri(Set<RedirectUriRM> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public Set<CAuthoritiesRM> getcAuthorities() {
        return cAuthorities;
    }

    public void setcAuthorities(Set<CAuthoritiesRM> cAuthorities) {
        this.cAuthorities = cAuthorities;
    }
}
