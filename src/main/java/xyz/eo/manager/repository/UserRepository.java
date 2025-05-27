package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.eo.manager.entity.User;
import xyz.eo.manager.validator.readOnlyQuery;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE user_id = ?1", nativeQuery = true)
    @readOnlyQuery
    @Modifying
    Optional<User> getUserById(Long userId);

    @Query(value = "SELECT * FROM user WHERE username =?1 AND email =?2 AND phone", nativeQuery = true)
    Optional<User> findByUser(String username, String email, String phone);

    @Query(value = "SELECT * FROM user WHERE username =?1 AND email =?2", nativeQuery = true)
    Optional<User> findByUser(String username, String email);

    @Query(value = "SELECT * FROM user WHERE user_id = ?1", nativeQuery = true)
    Optional<User> findByUserId(Long userId);

    @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE mobile = ?1", nativeQuery = true)
    Optional<User> findByMobile(String mobile);

    @Query("SELECT u FROM User u WHERE u.email = :input OR u.mobile = :input")
    Optional<User> findByEmailOrMobile(@Param("input") String input);
}
