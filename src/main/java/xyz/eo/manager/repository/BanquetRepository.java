package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eo.manager.entity.Banquet;

public interface BanquetRepository extends JpaRepository<Banquet, Long> {
}
