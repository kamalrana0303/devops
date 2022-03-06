package com.indiastarzilla.developers.ui.enums;

import java.util.Arrays;
import java.util.List;

public enum RoleName {
    USER(Arrays.asList(PermissionName.READ)),
    ADMIN(Arrays.asList(PermissionName.READ, PermissionName.WRITE));

    private List<PermissionName> permissions;

    RoleName(List<PermissionName> permissions) {
        this.permissions = permissions;
    }

    public List<PermissionName> getPermissions() {
        return permissions;
    }
}
