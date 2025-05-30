package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eo.manager.entity.EventType;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
