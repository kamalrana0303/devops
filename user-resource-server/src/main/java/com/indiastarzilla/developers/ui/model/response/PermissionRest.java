package com.indiastarzilla.developers.ui.model.response;

import com.indiastarzilla.developers.ui.enums.PermissionName;

public class PermissionRest {
    private PermissionName name;

    public PermissionName getName() {
        return name;
    }

    public void setName(PermissionName name) {
        this.name = name;
    }
}
