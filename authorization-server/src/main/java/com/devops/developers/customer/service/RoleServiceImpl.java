package com.devops.developers.customer.service;

import com.devops.developers.customer.dao.RoleDao;
import com.devops.developers.customer.entity.Permission;
import com.devops.developers.customer.entity.PermissionName;
import com.devops.developers.customer.entity.Role;
import com.devops.developers.customer.entity.RoleName;
import com.devops.developers.dto.PermissionDto;
import com.devops.developers.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements  RoleService{
    private final RoleDao roleDao;
    private final PermissionService permissionService;
    private final ModelMapper mapper;

    public RoleServiceImpl(RoleDao roleDao, PermissionService permissionService, ModelMapper mapper) {
        this.roleDao = roleDao;
        this.permissionService= permissionService;
        this.mapper= mapper;
    }

    @Override
    public RoleDto createRole(RoleName name){
        Role role=this.roleDao.findRoleByName(name);
        if(role==null){
            List<PermissionName> permissionNames=name.getPermissions();
            List<Permission> permissions= new ArrayList<>();
            for(PermissionName permissionName: permissionNames){
                PermissionDto permission= this.permissionService.createPermission(permissionName);
                if(permission==null){
                    throw new RuntimeException("invalid permission");
                }
                permissions.add(mapper.map(permission, Permission.class));
            }
            role= new Role(name);
            for(Permission permission: permissions){
                role.addPermission(permission);
            }
            role=this.roleDao.save(role);
        }
        return mapper.map(role, RoleDto.class);
    }

    @Override
    public RoleDto getRole(RoleName roleName){
        Role role=this.roleDao.findRoleByName(roleName);
        if(role==null){
            throw new RuntimeException("invalid role");
        }
        return  mapper.map(role, RoleDto.class);
    }

}
