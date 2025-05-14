package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "guest_event_detail",
        indexes = {
                @Index(name = "idx_user_event", columnList = "user_id, event_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_event", columnNames = {"user_id", "event_id"})
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class GuestEventDetail extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_event_id")
    private Long guestEventId;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "event_id", nullable = false)
    private Long eventId;
}