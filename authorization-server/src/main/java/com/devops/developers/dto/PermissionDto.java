package com.devops.developers.dto;

import com.devops.developers.customer.entity.PermissionName;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PermissionDto {

    private Long id;

    private PermissionName name;

    public PermissionDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermissionName getName() {
        return name;
    }

    public void setName(PermissionName name) {
        this.name = name;
    }
}
