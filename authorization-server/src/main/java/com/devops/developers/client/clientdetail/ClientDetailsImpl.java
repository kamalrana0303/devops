package com.devops.developers.client.clientdetail;

import com.devops.developers.client.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientDetailsImpl implements ClientDetails {

    private final Client client;

    public ClientDetailsImpl(Client client) {
        this.client = client;
    }

    @Override
    public String getClientId() {
        return this.client.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        Set<String> values = new HashSet<>();
        this.client.getResourceIds().forEach(resourceId -> {
            values.add(resourceId.getName());
        });
        return values;
    }

    @Override
    public boolean isSecretRequired() {
        return this.client.isSecretRequired();
    }

    @Override
    public String getClientSecret() {
        return this.client.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return this.client.isScoped();
    }

    @Override
    public Set<String> getScope() {
        Set<String> values = new HashSet<>();
        this.client.getScopes().forEach(scope -> values.add(scope.getName()));
        return values;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> values = new HashSet<>();
        this.client.getAuthorizedGrantTypes().forEach(grantTypes -> values.add(grantTypes.getName()));
        return values;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        Set<String> values= new HashSet<>();
        this.client.getRegisteredRedirectUri().forEach(r-> values.add(r.getName()));
        return values;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> values = new HashSet<>();
        this.client.getcAuthorities().forEach(a -> {
            values.add(new SimpleGrantedAuthority(a.getName()));
        });
        return values;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.client.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.client.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String s) {
        return this.client.isAutoApprove();
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }


}
