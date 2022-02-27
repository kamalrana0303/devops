package com.devops.developers.customer.model.response;

import com.devops.developers.customer.model.response.RoleRest;



import java.util.Set;

public class CustomerRest {
    private String email;

    private String phoneNumber;

    private String username;

    private Set<RoleRest> roles;

    public CustomerRest(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<RoleRest> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleRest> roles) {
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
