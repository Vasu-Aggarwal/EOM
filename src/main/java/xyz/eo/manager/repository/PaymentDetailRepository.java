package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eo.manager.entity.PaymentDetail;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {
}
