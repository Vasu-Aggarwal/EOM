package xyz.eo.manager.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddUpdateEventTypeResponse {

    private Long eventTypeId;
    private String eventTypeName;
    private Long banquetId;
    private Integer status;

}
