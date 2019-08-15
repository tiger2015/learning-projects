package com.tiger.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
@Slf4j
public class MyApplication implements CommandLineRunner {


    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(MyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {  //在初始化之前执行，即在main函数之前执行
        log.info("run function");
    }
}
