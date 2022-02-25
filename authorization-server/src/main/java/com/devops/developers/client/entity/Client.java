package com.devops.developers.client.entity;

import com.devops.developers.customer.entity.BaseId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends BaseId  {
    private String clientId;
    private boolean secretRequired=true;
    private String clientSecret;
    private boolean scoped=true;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private boolean autoApprove=false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "clients_resources", joinColumns = {@JoinColumn(name = "clients", referencedColumnName = "id") }, inverseJoinColumns = {@JoinColumn(name="resourceIds", referencedColumnName = "id")})
    private Set<Resource> resourceIds;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "clients_scopes", joinColumns = {@JoinColumn(name="clients", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name="scopes", referencedColumnName = "id")})
    private Set<Scope> scopes;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "clients_grant_types", joinColumns = {@JoinColumn(name = "clients", referencedColumnName = "id") }, inverseJoinColumns = {@JoinColumn(name="authorizedGrantTypes", referencedColumnName = "id")})
    private Set<GrantType> authorizedGrantTypes= new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "clients_registered_redirect_uris", joinColumns = {@JoinColumn(name="clients", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name="registeredRedirectUri", referencedColumnName = "id")})
    private Set<RedirectUri> registeredRedirectUri= new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_authorities", joinColumns = {@JoinColumn(name = "clients", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name="cAuthorities", referencedColumnName = "id")})
    private Set<CAuthorities> cAuthorities= new HashSet<>();

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

    public Set<Resource> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<Resource> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Set<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(Set<Scope> scopes) {
        this.scopes = scopes;
    }

    public Set<GrantType> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<GrantType> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public Set<RedirectUri> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    public void setRegisteredRedirectUri(Set<RedirectUri> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public Set<CAuthorities> getcAuthorities() {
        return cAuthorities;
    }

    public void setcAuthorities(Set<CAuthorities> cAuthorities) {
        this.cAuthorities = cAuthorities;
    }
}
