package com.devops.developers.client.model.request;

public class GrantTypeRM {
    private String name;

    public GrantTypeRM() {
    }

    public GrantTypeRM(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
