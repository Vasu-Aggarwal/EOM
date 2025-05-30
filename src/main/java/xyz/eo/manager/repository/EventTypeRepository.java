package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.eo.manager.dto.response.AddUpdateEventTypeResponse;
import xyz.eo.manager.entity.EventType;
import xyz.eo.manager.validator.readOnlyQuery;

import java.util.List;
import java.util.Optional;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {

    /**
     *
     * @param userId
     * @param banquetId
     * @param eventTypeId
     * @return Active event type
     */
    @readOnlyQuery
    @Query("""
            SELECT e FROM EventType e
                JOIN UserBanquetDetail ubd ON ubd.banquetId = e.banquetId
                WHERE e.id = :eventTypeId AND ubd.userId = :userId AND ubd.banquetId = :banquetId
                AND e.status = 1
            """)
    Optional<EventType> findEventTypeByUserBanquet(Long userId, Long banquetId, Long eventTypeId);

    /**
     *
     * @param userId
     * @param banquetId
     * @return List of event types which are in active state
     */
    @readOnlyQuery
    @Query("""
            SELECT new xyz.eo.manager.dto.response.AddUpdateEventTypeResponse(
            e.eventTypeId, e.eventTypeName, e.banquetId, e.status) FROM EventType e
            JOIN UserBanquetDetail ubd ON ubd.banquetId = e.banquetId
            WHERE e.id = :eventTypeId AND ubd.userId = :userId AND ubd.banquetId = :banquetId
            AND e.status = 1
            """)
    Optional<List<AddUpdateEventTypeResponse>> findAllEventTypeByUserBanquet(Long userId, Long banquetId);
}
