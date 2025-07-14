package com.dux.cnweb.domain.model.valueobjects;

import java.util.Set;

import com.dux.cnweb.domain.model.valueobjects.RolePermissionMapper.Permission;
import com.dux.cnweb.domain.model.valueobjects.RolePermissionMapper.Role;

import lombok.Value;

@Value(staticConstructor = "of")
public class AccessControlList {
    String unitId;
    Role role;

    public Set<Permission> getPermissions() {
        return RolePermissionMapper.getPermissions(role);
    }

    public boolean hasPermission(Permission permission) {
        return getPermissions().contains(permission);
    }
}
