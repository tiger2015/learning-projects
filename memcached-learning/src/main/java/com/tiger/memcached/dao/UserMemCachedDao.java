package com.tiger.memcached.dao;

import com.tiger.memcached.dto.User;

import java.util.List;

public interface UserMemCachedDao {

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);

    User selectUser(String key);

    List<User> listUser(String ...key);
}
