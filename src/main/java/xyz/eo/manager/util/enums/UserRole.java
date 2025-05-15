package xyz.eo.manager.util.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    SUPER_ADMIN(1),
    ADMIN(2),
    L1_MANAGER(3),
    L2_MANAGER(4),
    STORE_MANAGER(5),
    GUEST(6);

    private final Integer roleId;
    UserRole(Integer roleId) {
        this.roleId = roleId;
    }
}
