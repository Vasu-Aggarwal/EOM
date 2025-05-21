package xyz.eo.manager.dto.request.banquet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUpdateBanquetRequest {
    @JsonProperty(value = "banquet_id")
    private Long banquetId;
    @JsonProperty(value = "banquet_name")
    private String banquetName;
    @JsonProperty(value = "banquet_location")
    private String banquetLocation;
    private Integer status;
    @JsonProperty(value = "link_admin")
    private List<Long> linkAdmin;
}
