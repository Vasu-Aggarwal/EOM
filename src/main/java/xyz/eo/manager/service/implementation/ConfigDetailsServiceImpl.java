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
        String value = repository.findByKey(key).orElseThrow(() -> new ErrorMessageException("Invalid key", 0));
        return new ConfigKeyResponse(key, value);
    }

    @Override
    @Transactional
    public ConfigKeyResponse putConfigKeyDetails(String key, String value) {
        int updated = repository.updateValueByKey(key, value);
        if (updated == 0) {
            repository.save(new ConfigDetails(null, key, value));
        }
        return new ConfigKeyResponse(key, value);
    }
}
