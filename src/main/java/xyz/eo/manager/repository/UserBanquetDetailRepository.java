package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xyz.eo.manager.dto.model.UserBanquetDetailAndRoleDto;
import xyz.eo.manager.entity.UserBanquetDetail;
import xyz.eo.manager.validator.readOnlyQuery;

import java.util.Optional;

public interface UserBanquetDetailRepository extends JpaRepository<UserBanquetDetail, Long> {

    /**
     *
     * @param userId
     * @param banquetId
     * @return Active user mapped with that particular banquet with status as active.
     * @Note: statuses are stored in userBanquetStatus
     */
    @readOnlyQuery
    @Query("SELECT new xyz.eo.manager.dto.model.UserBanquetDetailAndRoleDto(ubd.status, u.roleId) FROM UserBanquetDetail ubd JOIN User u ON ubd.userId = u.userId WHERE ubd.userId = :userId AND ubd.banquetId = :banquetId AND ubd.status = 1")
    Optional<UserBanquetDetailAndRoleDto> getActiveUbdByUserAndBanquet(Long userId, Long banquetId);

    /**
     *
     * @param userId
     * @param banquetId
     * @return All the users with their current status. (ACTIVE, INACTIVE, DELETED)
     */
    @readOnlyQuery
    @Query("SELECT new xyz.eo.manager.dto.model.UserBanquetDetailAndRoleDto(ubd.status, u.roleId) FROM UserBanquetDetail ubd JOIN User u ON ubd.userId = u.userId WHERE ubd.userId = :userId AND ubd.banquetId = :banquetId")
    Optional<UserBanquetDetailAndRoleDto> getUbdByUserAndBanquet(Long userId, Long banquetId);

    /**
     *
     * @param userId
     * @param banquetId
     * @param status
     */
    @Modifying
    @Query("UPDATE UserBanquetDetail ubd SET ubd.status = :status WHERE ubd.userId = :userId AND ubd.banquetId = :banquetId")
    void updateStatus(Long userId, Long banquetId, Integer status);

}
