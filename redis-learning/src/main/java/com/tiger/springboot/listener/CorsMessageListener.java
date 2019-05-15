package com.tiger.springboot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CorsMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info("message:" + new String(message.getBody()));
        log.info("receive:" + new String(bytes));
    }
}
