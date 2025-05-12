package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eo.manager.entity.GuestEventDetail;

public interface GuestEventDetailRepository extends JpaRepository<GuestEventDetail, Long> {
}
