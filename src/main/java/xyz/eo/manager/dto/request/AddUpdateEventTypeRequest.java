package xyz.eo.manager.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddUpdateEventTypeRequest {

    @NotNull(message = "User ID cannot be null")
    private Long userId;
    private Long eventTypeId;

    @NotNull(message = "Banquet ID cannot be null.")
    private Long banquetId;

    @NotBlank(message = "Event type name cannot be blank.")
    private String eventTypeName;
}
