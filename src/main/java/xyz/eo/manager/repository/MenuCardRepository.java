package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eo.manager.entity.MenuCard;

public interface MenuCardRepository extends JpaRepository<MenuCard, Long> {
}
