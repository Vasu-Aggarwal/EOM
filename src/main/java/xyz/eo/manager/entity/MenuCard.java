package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.eo.manager.util.enums.MealType;

@Entity
@Table(name = "menu_card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MenuCard extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_card_id")
    private Long menuCardId;
    @Column(name = "banquet_id")
    private Long banquetId;
    @Column(name = "meal_type")
    private MealType mealType;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_desc")
    private String menuDescription;
}