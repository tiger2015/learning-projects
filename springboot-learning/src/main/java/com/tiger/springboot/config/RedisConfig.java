package com.tiger.springboot.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // 方式1   通过构造函数
        // RedisCacheManager redisCacheManager = new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory), cacheConfiguration());

        // 方式2  通过builder
        RedisCacheManager.RedisCacheManagerBuilder cacheManagerBuilder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory);
        cacheManagerBuilder.cacheDefaults(cacheConfiguration(Duration.ofMinutes(30))); // 默认的过期时间为30minutes
        Map<String, RedisCacheConfiguration> cacheMap = new HashMap<>();
        cacheMap.put("loginLogCache", cacheConfiguration(Duration.ofSeconds(60)));
        cacheMap.put("userCache", cacheConfiguration(Duration.ofSeconds(30)));
        cacheManagerBuilder.withInitialCacheConfigurations(cacheMap);
        return cacheManagerBuilder.build();
    }


    private RedisCacheConfiguration cacheConfiguration(Duration duration) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig() // 前缀不添加缓存名称
                .disableCachingNullValues()
                .entryTtl(duration); // 过期时间
        return cacheConfiguration;
    }


    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        //设置序列化器
        redisTemplate.setDefaultSerializer(serializer);
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        //fastjson的序列化器
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        template.setValueSerializer(serializer);
        //设置序列化器
        template.setConnectionFactory(connectionFactory);
        return template;
    }

}
