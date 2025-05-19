package xyz.eo.manager.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// @AllArgsConstructor
// @Builder
public class Permissions {
    private Boolean canAddUpdateUser;
    private Boolean canViewUser;
    private Boolean canDeleteUser;
    private Boolean canUpdateBanquet;
    private Boolean canViewBanquet;
    private Boolean canAddUpdateMenu;
    private Boolean canDeleteMenu;
}