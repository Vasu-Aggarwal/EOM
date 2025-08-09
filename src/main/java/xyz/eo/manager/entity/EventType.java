package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "event_type",
        indexes = {
                @Index(name = "idx_event_type_banquet", columnList = "event_type_id, banquet_id"),
                @Index(name = "idx_event_type_name", columnList = "event_type_name")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_event_type_banquet", columnNames = {"banquet_id", "event_type_name"})
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EventType extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventTypeId;
    @Column(nullable = false)
    private Long banquetId;
    private String eventTypeName;
}
