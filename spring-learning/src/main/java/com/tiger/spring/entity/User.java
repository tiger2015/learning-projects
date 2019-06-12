package com.tiger.spring.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Component
@Scope(value="singleton")
public class User {
    private String userName;
    private Date loginTime;
    private String ip;

    public User() {
        System.out.println("调用构造函数");
    }

    @PostConstruct
    public void init() {
        System.out.println("调用初始化方法");
    }


    @PreDestroy
    public void destroy(){
        System.out.println("调用销毁方法");
    }

}
