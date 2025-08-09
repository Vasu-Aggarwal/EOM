package xyz.eo.manager.dto.response.banquet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetBanquetDetailsByIdResponse {
    private Long banquetId;
    private String banquetName;
    private String banquetLocation;
    private Integer status;
}
