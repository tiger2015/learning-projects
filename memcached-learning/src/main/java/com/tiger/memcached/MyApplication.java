package com.tiger.memcached;

import com.tiger.memcached.dao.UserMemCachedDao;
import com.tiger.memcached.dao.UserMemCachedDaoImpl;
import com.tiger.memcached.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MyApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        log.info("start application");

        UserMemCachedDao userMemCachedDao = context.getBean("userMemCachedDao", UserMemCachedDaoImpl.class);
        User user = new User();
        user.setAge(22);
        user.setName("tiger");
        List<String> favorates = new ArrayList<>();
        favorates.add("movies");
        favorates.add("reading");
        favorates.add("walking");
        user.setFavorites(favorates);
        userMemCachedDao.insertUser(user);

        User tiger = userMemCachedDao.selectUser("tiger");
        System.out.println(tiger.getName());
        System.out.println(tiger.getAge());
        System.out.println(tiger.getFavorites().size());


    }
}
