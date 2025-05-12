package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.eo.manager.entity.MenuCategory;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
}
