package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.eo.manager.entity.ConfigDetails;
import xyz.eo.manager.validator.readOnlyQuery;

import java.util.Optional;

public interface ConfigDetailsRepository extends JpaRepository<ConfigDetails, Integer> {

    @readOnlyQuery
    @Query("SELECT cd.value FROM ConfigDetails cd WHERE cd.configKey like :key")
    Optional<String> findByKey(String key);

    @Modifying
    @Query("UPDATE ConfigDetails c SET c.value = :value WHERE c.configKey = :key")
    int updateValueByKey(@Param("key") String key, @Param("value") String value);

}