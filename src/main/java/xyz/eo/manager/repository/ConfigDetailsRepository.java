package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import xyz.eo.manager.entity.ConfigDetails;
import xyz.eo.manager.validator.readOnlyQuery;

import java.util.Optional;

public interface ConfigDetailsRepository extends JpaRepository<ConfigDetails, Integer> {

    @readOnlyQuery
    @Query("SELECT cd.value FROM ConfigDetails cd WHERE cd.configKey like :key")
    Optional<String> findByKey(String key);

}