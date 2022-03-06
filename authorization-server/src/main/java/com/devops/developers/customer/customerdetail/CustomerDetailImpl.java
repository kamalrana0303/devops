package com.devops.developers.customer.customerdetail;

import com.devops.developers.customer.entity.Customer;
import com.devops.developers.customer.entity.Permission;
import com.devops.developers.customer.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomerDetailImpl implements UserDetails {

    private final Customer customer;

    public CustomerDetailImpl(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : customer.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
            for (Permission permission : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getName().name()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !customer.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !customer.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !customer.isCredentialNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return customer.isEnabled();
    }
}
