package xyz.eo.manager.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermissionDto {
    private Boolean canAddUpdateUser;
    private Boolean canViewUser;
    private Boolean canDeleteUser;
    private Boolean canUpdateBanquet;
    private Boolean canViewBanquet;
    private Boolean canAddUpdateMenu;
    private Boolean canDeleteMenu;
}
