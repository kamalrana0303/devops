package com.devops.developers.customer.service;

import com.devops.developers.customer.entity.PermissionName;
import com.devops.developers.dto.PermissionDto;

public interface PermissionService {
    PermissionDto createPermission(PermissionName name);
}
