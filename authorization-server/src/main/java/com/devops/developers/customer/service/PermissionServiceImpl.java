package com.devops.developers.customer.service;

import com.devops.developers.customer.dao.PermissionDao;
import com.devops.developers.customer.entity.Permission;
import com.devops.developers.customer.entity.PermissionName;
import com.devops.developers.dto.PermissionDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionDao permissionDao;
    private final ModelMapper mapper;

    public PermissionServiceImpl(PermissionDao permissionDao, ModelMapper mapper) {
        this.permissionDao = permissionDao;
        this.mapper = mapper;
    }

    @Override
    public PermissionDto createPermission(PermissionName name) {
        Permission permission = this.permissionDao.findPermissionByName(name);
        if (permission == null) {
            permission = new Permission(name);
            this.permissionDao.save(permission);
        }
        return mapper.map(permission, PermissionDto.class);
    }
}
