package com.tiger.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import com.tiger.springboot.listener.CorsMessageListener;
import com.tiger.springboot.service.ReceiverService;

import java.util.concurrent.Executors;

@Configuration
@Slf4j
public class RedisConfig {

    @Value("${topic}")
    private String topic;

    @Value("${pattern}")
    private String pattern;


    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter(new ReceiverService()), new ChannelTopic(topic));
        container.addMessageListener(new CorsMessageListener(),new PatternTopic(pattern));
        // 设置执行任务的线程池，如果不设置，则main结束后，订阅通知会退出
        container.setTaskExecutor(Executors.newFixedThreadPool(8));
        log.info("init message adapter");    
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