package xyz.eo.manager.dto.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBanquetDetailAndRoleDto {
    private Integer status;
    private Integer roleId;
}
