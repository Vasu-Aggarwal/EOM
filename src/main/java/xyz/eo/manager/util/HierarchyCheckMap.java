package xyz.eo.manager.util;

import xyz.eo.manager.util.enums.UserRole;

import java.util.*;

/**
 * HierarchyCheckMap
 * 1) Super Admin
 * 2) Admin
 * 3) L1 Manager
 * 4) L2 Manager
 * 5) Store Manager
 * 6) Customer
 */

public final class HierarchyCheckMap {
    private static final Map<Integer, List<Integer>> hierarchyMap;
    static {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(UserRole.SUPER_ADMIN.getRoleId(), Arrays.asList(UserRole.ADMIN.getRoleId()));
        map.put(UserRole.ADMIN.getRoleId(), Arrays.asList(UserRole.L1_MANAGER.getRoleId(), UserRole.L2_MANAGER.getRoleId(),UserRole.STORE_MANAGER.getRoleId(),UserRole.GUEST.getRoleId()));
        map.put(UserRole.L1_MANAGER.getRoleId(), Arrays.asList(UserRole.ADMIN.getRoleId(),UserRole.STORE_MANAGER.getRoleId(),UserRole.GUEST.getRoleId()));
        map.put(UserRole.L2_MANAGER.getRoleId(), Arrays.asList(UserRole.ADMIN.getRoleId(),UserRole.STORE_MANAGER.getRoleId(),UserRole.GUEST.getRoleId()));
        map.put(UserRole.STORE_MANAGER.getRoleId(), Arrays.asList(UserRole.GUEST.getRoleId()));
        hierarchyMap = Collections.unmodifiableMap(map);
    }

    public static boolean checkHierarchy(Integer roleId, Integer targetRoleId) {
        List<Integer> roles = hierarchyMap.get(roleId);
        if (roles == null) {
            return false;
        }
        return roles.contains(targetRoleId);
    }
}
