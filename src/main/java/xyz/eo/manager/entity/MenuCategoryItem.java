package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.eo.manager.util.enums.MealType;

@Entity
@Table(name = "menu_item",
        indexes = {
                @Index(name = "idx_menu_item_category", columnList = "menu_item_id, menu_category_id"),
                @Index(name = "idx_menu_item_name", columnList = "menu_item_name")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_menu_item_category", columnNames = {"menu_item_name", "menu_category_id"})
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MenuCategoryItem extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long menuItemId;
    @Column(name = "menu_item_name", nullable = false)
    private String menuItemName;
    @Column(name = "menu_item_desc")
    private String menuItemDesc;
    @Column(name = "sequence_no", nullable = false)
    private Integer sequenceNo;
    @Column(name = "menu_category_id", nullable = false)
    private Long menuCategoryId;
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;
    @Column(name = "img_url")
    private String imgUrl;
}
