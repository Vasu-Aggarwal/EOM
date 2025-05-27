package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user_banquet_detail",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_ubd_user_banquet", columnNames = {"banquet_id", "user_id"})
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserBanquetDetail extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_banquet_id")
    private Long userBanquetId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "banquet_id", nullable = false)
    private Long banquetId;

    @Builder
    public UserBanquetDetail(Long userId, Long banquetId, Integer status){
        this.userId = userId;
        this.banquetId = banquetId;
        super.setStatus(status);
    }
}
