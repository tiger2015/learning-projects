package com.tiger.spring.entity;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class User {
    private String userName;
    private Date loginTime;
    private String ip;

    public User() {
        System.out.println("调用构造函数");
    }


    public void setUserName(String userName) {
        System.out.println("设置用户名");
        this.userName = userName;
    }

    @PostConstruct
    public void init() {
        System.out.println("调用初始化方法");
    }

}
