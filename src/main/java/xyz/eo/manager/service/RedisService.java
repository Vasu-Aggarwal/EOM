package xyz.eo.manager.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface RedisService {

    /**
     * Get an object by key and deserialize to specified class type.
     * @param key Redis key
     * @param entityClass Class type to deserialize to
     * @param <T> Type of object
     * @return deserialized object or null if not found
     */
    <T> T get(String key, Class<T> entityClass);

    /**
     * Set an object into Redis with an optional TTL (time to live).
     * @param key Redis key
     * @param object Object to store
     * @param ttl Time to live in seconds; if null or zero, no expiration
     */
    void set(String key, Object object, Long ttl);

    /**
     * Get a list of objects by key, deserialized using Jackson TypeReference.
     * Useful for generic lists.
     * @param key Redis key
     * @param typeReference Jackson type reference for list type
     * @param <T> Type of list elements
     * @return deserialized list or null if not found
     */
    <T> List<T> getList(String key, TypeReference<List<T>> typeReference);

    /**
     * Optional: Delete a key from Redis.
     * @param key Redis key to delete
     */
    void delete(String key);

    /**
     * Optional: Check if a key exists.
     * @param key Redis key
     * @return true if exists, false otherwise
     */
    boolean exists(String key);
}

