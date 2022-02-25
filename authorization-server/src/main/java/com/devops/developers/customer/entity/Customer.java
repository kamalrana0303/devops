package com.devops.developers.customer.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends BaseId implements UserDetails, Serializable {
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean enabled;
    @Column(name="account_locked")
    private boolean accountNonLocked;
    @Column(name="account_expired")
    private boolean accountNonExpired;
    @Column(name="credential_expired")
    private boolean credentialNonExpired;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="role_customer", joinColumns = {
            @JoinColumn(name = "customer_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name="role_id", referencedColumnName = "id")
    })
    private Set<Role> roles= new HashSet<>();

    public Customer(){}

    public void addRole(Role role){
        roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities= new HashSet<>();
        roles.forEach(role->{
            //authorities.add(new SimpleGrantedAuthority(role.getName().name()));
            role.getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getName().name()));
            });
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return !this.accountNonLocked;
    }
    @Override
    public boolean isAccountNonLocked() {
        return !this.accountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialNonExpired;
    }

    public boolean isCredentialNonExpired() {
        return credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setCredentialNonExpired(boolean credentialNonExpired) {
        this.credentialNonExpired = credentialNonExpired;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
