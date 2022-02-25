package com.devops.developers.customer.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Role extends BaseId implements Serializable {
    @Column(nullable = false,unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="permission_role", joinColumns = {
            @JoinColumn(name="role_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name="permission_id", referencedColumnName = "id")
    })
    private Set<Permission> permissions= new HashSet<>();

    public Role(){}



    public Role(RoleName name) {
        this.name = name;
    }

    public void addPermission(Permission permission){
        this.permissions.add(permission);
    }

    public void removePermission(Permission permission){
        permissions.remove(permission);
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }


}
