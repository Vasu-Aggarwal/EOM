package xyz.eo.manager.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class addUpdateUserResponse {
    private String status;
    private String message;
}
