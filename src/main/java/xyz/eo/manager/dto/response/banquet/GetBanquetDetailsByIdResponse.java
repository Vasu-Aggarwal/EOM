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
    @JsonProperty(value = "banquet_id")
    private Long banquetId;
    @JsonProperty(value = "banquet_name")
    private String banquetName;
    @JsonProperty(value = "banquet_location")
    private String banquetLocation;
    private Integer status;
}
