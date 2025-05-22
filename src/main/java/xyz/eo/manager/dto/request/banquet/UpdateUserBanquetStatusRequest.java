package xyz.eo.manager.dto.request.banquet;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import xyz.eo.manager.util.enums.UserBanquetStatus;
import xyz.eo.manager.validator.ValidStatusFromEnum;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserBanquetStatusRequest {

    @NotNull(message = "User Id cannot be null")
    @JsonProperty(value = "user_id")
    private Long userId;

    @NotNull(message = "Banquet Id cannot be null")
    @JsonProperty(value = "banquet_id")
    private Long banquetId;

    @NotNull(message = "Status cannot be null")
    @ValidStatusFromEnum(message = "Invalid Status", enumClass = UserBanquetStatus.class)
    private Integer status;
}
