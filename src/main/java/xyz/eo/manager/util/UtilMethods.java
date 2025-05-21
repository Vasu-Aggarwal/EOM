package xyz.eo.manager.util;

import xyz.eo.manager.util.enums.UserRole;

public class UtilMethods {
    public static boolean isSuperAdmin(String role) {
        return UserRole.SUPER_ADMIN.name().equals(role);
    }

    public static boolean isAdmin(String role) {
        return UserRole.ADMIN.name().equals(role);
    }
}
