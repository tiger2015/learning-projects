package com.tiger.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BeanLifeClycle implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("set bean factory");

    }

    @Override
    public void setBeanName(String name) {
        log.info("set bean name:" + name);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("after properties set");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        log.info("set application context");

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("post process before init, bean name:" + beanName);

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        log.info("post process after init, bean name:" + beanName);
        return bean;
    }
}
