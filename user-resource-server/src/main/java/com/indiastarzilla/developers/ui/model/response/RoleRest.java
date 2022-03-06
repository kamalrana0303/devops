package com.indiastarzilla.developers.ui.model.response;


import com.indiastarzilla.developers.ui.enums.RoleName;

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
