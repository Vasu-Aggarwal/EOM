package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eo.manager.entity.MenuCategoryItem;

public interface MenuCardItemRepository extends JpaRepository<MenuCategoryItem, Long> {
}
