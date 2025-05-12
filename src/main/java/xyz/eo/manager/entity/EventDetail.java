package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.eo.manager.util.enums.FunctionType;
import xyz.eo.manager.util.enums.MealTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EventDetail extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;
    @Column(name = "banquet_id", nullable = false)
    private Long banquetId;
    @Column(name = "meal_time", nullable = false)
    private MealTime mealTime;
    @Column(name = "function_type", nullable = false)
    private FunctionType functionType;
    @Column(name = "date_booked", nullable = false)
    private LocalDate dateBooked;
    @Column(name = "menu_type_id", nullable = false)
    private Long menuTypeId;
    @Column(name = "pax_count", nullable = false)
    private Integer paxCount;
    @Column(name = "flower_amount")
    private Double flowerAmount;
    @Column(name = "dj_amount")
    private Double djAmount;
    @Column(name = "cocktail_amount")
    private Double cocktailAmount;
    @Column(name = "fruit_amount")
    private Double fruitAmount;
    @Column(name = "extra_event_amount")
    private Double extraEventAmount;
    @Column(name = "total_package_amount", nullable = false)
    private Double totalPackageAmount;
}