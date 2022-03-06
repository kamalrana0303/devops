package com.devops.developers.client.entity;

import com.devops.developers.customer.entity.BaseId;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CAuthorities extends BaseId {
    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
