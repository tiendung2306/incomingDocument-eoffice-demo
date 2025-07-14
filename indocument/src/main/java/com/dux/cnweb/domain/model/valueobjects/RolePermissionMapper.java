package com.dux.cnweb.domain.model.valueobjects;

import java.util.Map;
import java.util.Set;

public class RolePermissionMapper {

    public enum Role {
        CHU_TRI,
        PHOI_HOP,
        XEM_DE_BIET,
    }

    public enum Permission {
        CHUYEN_CT,
        GIAO_VIEC,
        TRA_LAI,
        KET_THUC,
        CHUYEN_PH,
        BAO_CAO,
        CHO_YKIEN,
        THU_HOI,
    }

    private static final Map<Role, Set<Permission>> rolePermissionMap = Map.of(
            Role.CHU_TRI,
            Set.of(Permission.CHUYEN_CT, Permission.GIAO_VIEC, Permission.TRA_LAI, Permission.KET_THUC,
                    Permission.BAO_CAO, Permission.CHO_YKIEN, Permission.THU_HOI),
            Role.PHOI_HOP,
            Set.of(Permission.CHUYEN_PH, Permission.GIAO_VIEC, Permission.TRA_LAI, Permission.BAO_CAO,
                    Permission.KET_THUC, Permission.THU_HOI),
            Role.XEM_DE_BIET, Set.of());

    public static Set<Permission> getPermissions(Role role) {
        return rolePermissionMap.getOrDefault(role, Set.of());
    }
}
