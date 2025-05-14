package xyz.eo.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import xyz.eo.manager.util.enums.PaymentMode;

@Entity
@Table(name = "payment_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PaymentDetail extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;
    @Column(nullable = false)
    private Double amount;
    @Column(name = "payment_mode", nullable = false)
    private PaymentMode paymentMode;
    private String description;
    @Column(name = "event_id", nullable = false)
    private Long eventId;
}