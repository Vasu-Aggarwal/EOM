package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.eo.manager.util.enums.MealType;

@Entity
@Table(name = "menu_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MenuCardItem extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long menuItemId;
    private String name;
    private String description;
    @Column(name = "sequence_no")
    private Integer sequenceNo;
    @Column(name = "menu_category_id")
    private Long menuCategoryId;
    @Column(name = "meal_type")
    private MealType mealType;
    @Column(name = "img_url")
    private String imgUrl;
}
