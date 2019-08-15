package com.tiger.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedisClusterTest {

    @Autowired
    private StringRedisTemplate template;
    @Test
    public void test01(){
        template.opsForValue().set("test1","test1");
    }

    @Test
    public void test02(){
        String test = template.opsForValue().get("test1");
        log.info("result:"+test);

    }
}
