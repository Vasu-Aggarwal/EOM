package xyz.eo.manager.dto.request.banquet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import xyz.eo.manager.util.enums.BanquetStatus;
import xyz.eo.manager.validator.ValidStatusFromEnum;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddUpdateBanquetRequest {
    private Long userId;
    private Long banquetId;
    private String banquetName;
    private String banquetLocation;

    @ValidStatusFromEnum(enumClass = BanquetStatus.class)
    private Integer status;
    private List<Long> linkAdmin;
}
