package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.eo.manager.entity.User;
import xyz.eo.manager.validator.readOnlyQuery;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE user_id = ?1", nativeQuery = true)
    @readOnlyQuery
    Optional<User> getUserById(Long userId);
}
