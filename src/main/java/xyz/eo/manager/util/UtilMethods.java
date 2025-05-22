package xyz.eo.manager.util;

import xyz.eo.manager.util.enums.UserRole;

public class UtilMethods {
    public static boolean isSuperAdmin(Integer role) {
        return UserRole.SUPER_ADMIN.getRoleId().equals(role);
    }

    public static boolean isAdmin(Integer role) {
        return UserRole.ADMIN.getRoleId().equals(role);
    }
}
