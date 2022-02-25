package com.devops.developers.customer.dao;

import com.devops.developers.customer.entity.Role;
import com.devops.developers.customer.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
    public Role findRoleByName(RoleName name);
}
