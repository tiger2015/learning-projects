package com.tiger.memcached;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@Configuration
@PropertySource(value = {"classpath:sysconfig.properties", "file:./config/sysconfig.properties"}, ignoreResourceNotFound = true)
@ComponentScan(basePackages = {"com.tiger.memcached"})
@Slf4j
public class ApplicationConfig {

    static{
        File config=new File("./config/log4j2.xml");
        try {
            if(config.exists()){
                ConfigurationSource source = new ConfigurationSource(new FileInputStream(config), config);
                Configurator.initialize(null, source);
                System.out.println("加载log4j2.xml");
            }
        } catch (FileNotFoundException e) {

        }
    }

    @Value("${memcached.servers}")
    private String[] memcachedServers;
    @Value("${memcached.initConnections}")
    private int initConnections;
    @Value("${memcached.minConnections}")
    private int minConnections;
    @Value("${memcached.maxConnections}")
    private int maxConnections;
    @Value("${memcached.maxIdle}")
    private int maxIdle;
    @Value("${memcached.socketConnectionTimeout}")
    private int socketConnectionTimeout;
    @Value("${memcached.socketTimeout}")
    private int socketTimeout;
    @Value("${memcahced.poolName}")
    private String memcachedPoolName;

    @Bean(destroyMethod = "shutDown", initMethod = "initialize")
    public SockIOPool memcachedPool() {
        SockIOPool pool = SockIOPool.getInstance(memcachedPoolName);
        pool.setServers(memcachedServers);
        pool.setInitConn(initConnections);
        pool.setMinConn(minConnections);
        pool.setMaxConn(maxConnections);
        pool.setMaxIdle(maxIdle);
        pool.setSocketConnectTO(socketConnectionTimeout);
        pool.setSocketTO(socketTimeout);
        return pool;
    }

    @Bean
    public MemCachedClient memCachedClient(){
        MemCachedClient client = new MemCachedClient(memcachedPoolName);
        return client;
    }
}
