package com.devops.developers.customer.model.response;

import com.devops.developers.customer.entity.PermissionName;

public class PermissionRest {
    private PermissionName name;

    public PermissionName getName() {
        return name;
    }

    public void setName(PermissionName name) {
        this.name = name;
    }
}
