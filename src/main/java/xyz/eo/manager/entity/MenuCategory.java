package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "menu_category",
        indexes = {
                @Index(name = "idx_menu_category_name", columnList = "menu_category_name")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_menu_category_name_card", columnNames = {"menu_category_name", "menu_card_id"})
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MenuCategory extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_category_id")
    private Long menuCategoryId;
    @Column(name = "menu_category_name", nullable = false)
    private String menuCategoryName;
    @Column(name = "menu_category_desc")
    private String menuCategoryDesc;
    @Column(name = "sequence_no", nullable = false)
    private Integer sequenceNo;
    @Column(name = "menu_card_id", nullable = false)
    private Long menuCardId;
}
