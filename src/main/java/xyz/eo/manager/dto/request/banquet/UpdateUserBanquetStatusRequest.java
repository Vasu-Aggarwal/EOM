package xyz.eo.manager.dto.request.banquet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import xyz.eo.manager.util.enums.UserBanquetStatus;
import xyz.eo.manager.validator.ValidStatusFromEnum;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UpdateUserBanquetStatusRequest {

    @NotNull(message = "User Id cannot be null")
    @NotBlank(message = "User Id cannot be empty")
    private Long userId;

    @NotNull(message = "Banquet Id cannot be null")
    @NotBlank(message = "Banquet Id cannot be empty")
    private Long banquetId;

    @NotNull(message = "Status cannot be null")
    @NotBlank(message = "Status cannot be empty")
    @ValidStatusFromEnum(message = "Invalid Status", enumClass = UserBanquetStatus.class)
    private Integer status;
}
