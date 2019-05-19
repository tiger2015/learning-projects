package com.tiger.springboot.dao;

import com.tiger.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 根据用户名查找用户
     *
     * @param userName
     * @return
     */
    User select(String userName);
}
