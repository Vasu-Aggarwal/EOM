package xyz.eo.manager.dto.request.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AddUpdateEventRequest {

    @JsonProperty("user_id")
    @NotNull(message = "User ID is required")
    private Long userId;

    @JsonProperty("event_id")
    private Long eventId;

    @NotNull(message = "Banquet ID is required")
    @JsonProperty("banquet_id")
    private Long banquetId;

    @NotNull(message = "Meal time is required")
    @JsonProperty("meal_time")
    private MealTime mealTime;

    @NotNull(message = "Function type is required")
    @JsonProperty("function_type")
    private FunctionType functionType;

    @NotNull(message = "Date booked is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date_booked")
    private LocalDate dateBooked;

    @NotNull(message = "Menu type ID is required")
    @JsonProperty("menu_type_id")
    private Long menuTypeId;

    @NotNull(message = "Pax count is required")
    @JsonProperty("pax_count")
    private Integer paxCount;

    @JsonProperty("flower_amount")
    private Double flowerAmount;

    @JsonProperty("dj_amount")
    private Double djAmount;

    @JsonProperty("cocktail_amount")
    private Double cocktailAmount;

    @JsonProperty("fruit_amount")
    private Double fruitAmount;

    @JsonProperty("extra_event_amount")
    private Double extraEventAmount;

    @NotNull(message = "Total package amount is required")
    @JsonProperty("total_package_amount")
    private Double totalPackageAmount;
}
