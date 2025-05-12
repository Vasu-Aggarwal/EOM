package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eo.manager.entity.EventDetail;

public interface EventDetailRepository extends JpaRepository<EventDetail, Long> {
}
