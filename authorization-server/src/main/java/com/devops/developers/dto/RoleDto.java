package com.devops.developers.dto;

import com.devops.developers.customer.entity.Permission;
import com.devops.developers.customer.entity.RoleName;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class RoleDto {

    private Long id;

    private RoleName name;

    private Set<PermissionDto> permissions;

    public RoleDto(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleDto(RoleName roleName){
        this.name=roleName;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Set<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDto> permissions) {
        this.permissions = permissions;
    }
}
