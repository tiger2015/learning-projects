package com.tiger.spring;

import com.tiger.spring.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Appliaction {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        User bean = context.getBean(User.class);



    }
}
