package com.devops.developers.client.entity;

import com.devops.developers.customer.entity.BaseId;

import javax.persistence.*;
import java.util.Set;

@Entity
public class RedirectUri extends BaseId {
    @Column(unique = true, nullable = false)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
