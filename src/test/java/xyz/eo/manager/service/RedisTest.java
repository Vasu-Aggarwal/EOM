package xyz.eo.manager.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    @Disabled
    void testRedis(){
        redisTemplate.opsForValue().set("email", "test@test.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a= 1;
    }
}
