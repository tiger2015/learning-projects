package com.tiger.springboot.listener;

import com.tiger.springboot.config.RedisClusterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

@Slf4j
@Component
public class RedisMessageListenerFactory implements BeanFactoryAware, ApplicationListener<ContextRefreshedEvent> {
    private DefaultListableBeanFactory beanFactory;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private RedisClusterConfig clusterConfig;

    public RedisMessageListenerFactory() {
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        RedisClusterConnection connection = connectionFactory.getClusterConnection();
        String password = clusterConfig.getPassword();
        if (connection != null) {
            Iterable<RedisClusterNode> nodes = connection.clusterGetNodes();
            for (RedisClusterNode node : nodes) {
                if (node.isMaster()) {
                    String containerName = "messageContainer" + node.hashCode();
                    if (beanFactory.containsBean(containerName)) {
                        log.warn("bean:" + containerName + " exists.");
                        return;
                    }
                    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(node.getHost(), node.getPort().intValue());
                    configuration.setPassword(password);
                    JedisConnectionFactory connectionFactory = new JedisConnectionFactory(configuration);
                    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RedisMessageListenerContainer.class);
                    beanDefinitionBuilder.addPropertyValue("connectionFactory", connectionFactory);
                    beanDefinitionBuilder.setScope(BeanDefinition.SCOPE_SINGLETON);
                    beanDefinitionBuilder.setLazyInit(false);
                    beanFactory.registerBeanDefinition(containerName, beanDefinitionBuilder.getRawBeanDefinition());
                    RedisMessageListenerContainer listenerContainer = beanFactory.getBean(containerName, RedisMessageListenerContainer.class);
                    String listenerBeanName = "messageListener" + node.hashCode();
                    if (beanFactory.containsBean(listenerBeanName)) {
                        return;
                    }
                    listenerContainer.addMessageListener(new CorsMessageListener(), new PatternTopic("__keyspace@0__:message*"));
                    listenerContainer.setTaskExecutor(Executors.newFixedThreadPool(8));
                    listenerContainer.start();
                    log.info(node.getHost() + ":" + node.getPort() + " start message listener");
                }
            }
        }

    }
}
