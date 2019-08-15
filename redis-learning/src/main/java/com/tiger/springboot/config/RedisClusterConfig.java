package com.tiger.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@ConfigurationProperties(prefix = "redis.cluster")
public class RedisClusterConfig {
    private List<RedisNode> nodes;
    private String password;

    public List<RedisNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<RedisNode> nodes) {
        this.nodes = nodes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
