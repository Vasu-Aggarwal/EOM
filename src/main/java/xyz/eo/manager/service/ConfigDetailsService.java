package xyz.eo.manager.service;

import xyz.eo.manager.dto.response.ConfigKeyResponse;

public interface ConfigDetailsService {
    ConfigKeyResponse getConfigKeyDetails(String key);
    ConfigKeyResponse putConfigKeyDetails(String key, String value);
}
