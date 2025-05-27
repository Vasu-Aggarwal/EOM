package xyz.eo.manager.dto.request;

import lombok.*;
import xyz.eo.manager.util.Permissions;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUpdateUserRequest {
    private Long userId;
    private String name;
    private String email;
    private String mobile;
    private String userName;
    private String password;
    private Integer roleId;
    private Permissions permissions;
}
