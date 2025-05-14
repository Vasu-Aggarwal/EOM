package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "banquet",
        indexes = {
                @Index(name = "idx_banquet_name", columnList = "banquet_name"),
                @Index(name = "idx_banquet_name_location", columnList = "banquet_name, banquet_location")
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Banquet extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banquet_id")
    private Long banquetId;
    @Column(name = "banquet_name", nullable = false)
    private String banquetName;
    @Column(name = "banquet_location", nullable = false)
    private String location;
}