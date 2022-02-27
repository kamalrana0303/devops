package com.devops.developers.customer.model.response;

import com.devops.developers.customer.entity.RoleName;
import com.devops.developers.dto.PermissionDto;

import java.util.Set;

public class RoleRest {
    private RoleName name;

    private Set<PermissionRest> permissions;


    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Set<PermissionRest> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionRest> permissions) {
        this.permissions = permissions;
    }
}
