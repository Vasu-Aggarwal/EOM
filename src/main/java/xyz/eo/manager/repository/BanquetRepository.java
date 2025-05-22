package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.eo.manager.entity.Banquet;
import xyz.eo.manager.entity.User;
import xyz.eo.manager.validator.readOnlyQuery;

import java.util.Optional;

public interface BanquetRepository extends JpaRepository<Banquet, Long> {
    @Query("SELECT b FROM Banquet b WHERE b.banquetId = :banquetId")
    @readOnlyQuery
    Optional<Banquet> getBanquetById(Long banquetId);
}
