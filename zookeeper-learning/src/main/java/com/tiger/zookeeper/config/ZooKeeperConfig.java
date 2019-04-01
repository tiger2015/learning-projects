package com.tiger.zookeeper.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * @Auther: Administrator
 * @Date: 2019/3/30 15:26
 * @Description:
 */
@Configuration
@PropertySource(value = {"classpath:spring-config.properties"})
@ComponentScan(basePackages = {"com.tiger.zookeeper"})
@Slf4j
public class ZooKeeperConfig {
    @Value("${zookeeper.nodes}")
    private String zookeeperNodes;
    @Value("${zookeeper.sessionTimeout}")
    private int sessionTimeout;

    @Bean(destroyMethod = "close")
    public ZooKeeper zooKeeper() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(zookeeperNodes, sessionTimeout, null);
        } catch (IOException e) {
            log.error("创建zookeeper连接失败", e);
        }
        return zk;
    }
}
