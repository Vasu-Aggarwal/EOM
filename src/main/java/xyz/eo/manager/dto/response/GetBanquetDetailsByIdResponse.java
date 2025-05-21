package xyz.eo.manager.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetBanquetDetailsByIdResponse {
    @JsonProperty(value = "banquet_id")
    private Long banquetId;
    @JsonProperty(value = "banquet_name")
    private String banquetName;
    @JsonProperty(value = "banquet_location")
    private String banquetLocation;
    private Integer status;
}
