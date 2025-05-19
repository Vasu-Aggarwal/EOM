package xyz.eo.manager.dto.model;

import lombok.*;
import xyz.eo.manager.util.Permissions;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDto {
    private String name;
    private String email;
    private String mobile;
    private String userName;
    private String password;
    private Integer roleId;
    private Permissions permissions;
}
