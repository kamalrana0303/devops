package com.devops.developers.customer.dao;

import com.devops.developers.customer.entity.Permission;
import com.devops.developers.customer.entity.PermissionName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<Permission, Long> {
    public Permission findPermissionByName(PermissionName name);
}
