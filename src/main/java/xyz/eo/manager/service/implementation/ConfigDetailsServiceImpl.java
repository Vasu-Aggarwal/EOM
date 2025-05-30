package xyz.eo.manager.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.eo.manager.dto.response.ConfigKeyResponse;
import xyz.eo.manager.entity.ConfigDetails;
import xyz.eo.manager.exception.ErrorMessageException;
import xyz.eo.manager.repository.ConfigDetailsRepository;
import xyz.eo.manager.service.ConfigDetailsService;

@Service
public class ConfigDetailsServiceImpl implements ConfigDetailsService {

    private final ConfigDetailsRepository repository;

    public ConfigDetailsServiceImpl(ConfigDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConfigKeyResponse getConfigKeyDetails(String key) {
        return repository.findByKey(key).map(e -> new ConfigKeyResponse(e.getConfigKey(), e.getValue())).orElseThrow(() -> new ErrorMessageException("Key does not exists", 0));
    }

    @Override
    @Transactional
    public ConfigKeyResponse putConfigKeyDetails(String key, String value) {
        ConfigDetails entity = repository.findByKey(key)
                .map(existing -> {
                    existing.setConfigKey(key);
                    existing.setValue(value);
                    return existing;
                })
                .orElseGet(() -> new ConfigDetails(null, key, value));

        repository.save(entity);
        return new ConfigKeyResponse(entity.getConfigKey(), entity.getValue());
    }
}
