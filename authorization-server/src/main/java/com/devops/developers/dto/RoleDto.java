package com.devops.developers.dto;

import com.devops.developers.customer.entity.RoleName;

import java.util.Set;

public class RoleDto {

    private Long id;

    private RoleName name;

    private Set<PermissionDto> permissions;

    public RoleDto() {
    }


    public RoleDto(RoleName roleName) {
        this.name = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
