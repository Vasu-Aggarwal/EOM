package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.eo.manager.util.enums.MealType;

@Entity
@Table(name = "menu_card",
        indexes = {
                @Index(name = "idx_menu_banquet", columnList = "menu_card_id, banquet_id"),
                @Index(name = "idx_menu_name_banquet", columnList = "menu_name, banquet_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_menu_name_per_banquet", columnNames = {"banquet_id", "menu_name"})
        })
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
    @Column(name = "banquet_id", nullable = false)
    private Long banquetId;
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;
    @Column(name = "menu_name", nullable = false)
    private String menuName;
    @Column(name = "menu_desc")
    private String menuDesc;
}