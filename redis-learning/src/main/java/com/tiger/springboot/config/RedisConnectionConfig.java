package com.tiger.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import com.tiger.springboot.listener.CorsMessageListener;
import com.tiger.springboot.service.ReceiverService;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.concurrent.Executors;

@Configuration
@Slf4j
public class RedisConnectionConfig {

    @Autowired
    private RedisClusterConfig clusterConfig;

    @Value("${redis.cluster.pool.max-active}")
    private int maxTotal;
    @Value("${redis.cluster.pool.min-idle}")
    private int minIdle;
    @Value("${redis.cluster.pool.max-idle}")
    private int maxIdle;
    @Value("${redis.cluster.pool.max-wait}")
    private long maxWait;

    @Value("${topic}")
    private String topic;

    @Value("${pattern}")
    private String pattern;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        return poolConfig;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        for (RedisNode node : clusterConfig.getNodes()) {
            clusterConfiguration.addClusterNode(new RedisClusterNode(node.getIp(), node.getPort()));
        }
        clusterConfiguration.setPassword(clusterConfig.getPassword());
        RedisConnectionFactory connectionFactory = new JedisConnectionFactory(clusterConfiguration, jedisPoolConfig);
        return connectionFactory;
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter(new ReceiverService()), new ChannelTopic(topic));
        container.addMessageListener(new CorsMessageListener(), new PatternTopic(pattern));
        // 设置执行任务的线程池，如果不设置，则main结束后，订阅通知会退出
        container.setTaskExecutor(Executors.newFixedThreadPool(8));
        log.info("init message listener container");
        return container;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    MessageListenerAdapter listenerAdapter(ReceiverService receiverService) {
        return new MessageListenerAdapter(receiverService, "handleMessage");
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}