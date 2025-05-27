package xyz.eo.manager.dto.response.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUpdateEventResponse {
    @JsonProperty("event_id")
    private Long eventId;
}
