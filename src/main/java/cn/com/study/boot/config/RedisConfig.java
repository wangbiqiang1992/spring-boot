package cn.com.study.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(CacheProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private CacheProperties cacheProperties;

    @Bean("redisCacheManager")
    @Primary
    public RedisCacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);
        Map<String, Long> expires = new HashMap<>();
        cacheManager.setDefaultExpiration(10 * 60);
        expires.put("employee", 10 * 60L);
        cacheManager.setExpires(expires);
        List<String> cacheNames = cacheProperties.getCacheNames();
        if (!CollectionUtils.isEmpty(cacheNames)) {
            cacheManager.setCacheNames(cacheNames);
        }
        return cacheManager;
    }

    @Bean("redisCacheManagerString")
    public RedisCacheManager cacheManagerString(StringRedisTemplate stringRedisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(stringRedisTemplate);
        redisCacheManager.setUsePrefix(true);
        redisCacheManager.setDefaultExpiration(10 * 60);
        List<String> cacheNames = cacheProperties.getCacheNames();
        if (!CollectionUtils.isEmpty(cacheNames)) {
            redisCacheManager.setCacheNames(cacheNames);
        }
        return redisCacheManager;
    }
}
