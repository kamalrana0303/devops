package com.devops.developers.client.model.request;

public class CAuthoritiesRM {
    private String name;

    public CAuthoritiesRM(){}

    public CAuthoritiesRM(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
