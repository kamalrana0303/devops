package com.devops.developers.customer.service;

import com.devops.developers.customer.entity.Role;
import com.devops.developers.customer.entity.RoleName;
import com.devops.developers.dto.RoleDto;

public interface RoleService {
    RoleDto createRole(RoleName name);

    RoleDto getRole(RoleName roleName);
}
