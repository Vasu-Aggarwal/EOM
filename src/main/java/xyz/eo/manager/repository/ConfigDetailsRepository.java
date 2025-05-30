package xyz.eo.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.eo.manager.entity.ConfigDetails;
import xyz.eo.manager.validator.readOnlyQuery;

import java.util.Optional;

public interface ConfigDetailsRepository extends JpaRepository<ConfigDetails, Integer> {

    @readOnlyQuery
    Optional<ConfigDetails> findByConfigKey(String key);

}