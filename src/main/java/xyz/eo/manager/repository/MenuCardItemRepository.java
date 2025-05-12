package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eo.manager.entity.MenuCardItem;

public interface MenuCardItemRepository extends JpaRepository<MenuCardItem, Long> {
}
