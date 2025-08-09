package xyz.eo.manager.dto.request.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.eo.manager.util.enums.FunctionType;
import xyz.eo.manager.util.enums.MealTime;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddUpdateEventRequest {

    @NotNull(message = "User ID is required")
    private Long userId;
    private Long eventId;

    @NotNull(message = "Banquet ID is required")
    private Long banquetId;
    private MealTime mealTime;
    private FunctionType functionType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateBooked;
    private Long menuTypeId;
    private Integer paxCount;
    private Double flowerAmount;
    private Double djAmount;
    private Double cocktailAmount;
    private Double fruitAmount;
    private Double extraEventAmount;
    private Double totalPackageAmount;
}
