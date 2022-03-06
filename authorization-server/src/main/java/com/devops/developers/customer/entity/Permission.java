package com.devops.developers.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
public class Permission extends BaseId implements Serializable {
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PermissionName name;

    public Permission() {
    }

    public Permission(PermissionName name) {
        this.name = name;
    }

    public PermissionName getName() {
        return name;
    }

    public void setName(PermissionName name) {
        this.name = name;
    }
}
