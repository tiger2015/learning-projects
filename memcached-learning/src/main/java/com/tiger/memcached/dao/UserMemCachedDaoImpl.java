package com.tiger.memcached.dao;

import com.tiger.memcached.dto.User;
import com.whalin.MemCached.MemCachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("userMemCachedDao")
public class UserMemCachedDaoImpl implements UserMemCachedDao {

    @Autowired
    private MemCachedClient memCachedClient;

    @Override
    public boolean insertUser(User user) {
        return memCachedClient.set(user.getKey(), user);
    }

    @Override
    public boolean updateUser(User user) {
        return memCachedClient.add(user.getKey(), user);
    }

    @Override
    public boolean deleteUser(User user) {
        return memCachedClient.delete(user.getKey());
    }

    @Override
    public User selectUser(String key) {
        return (User) memCachedClient.get(key);
    }

    @Override
    public List<User> listUser(String... key) {
        Map<String, Object> objectMap = memCachedClient.getMulti(key);
        List<User> userList = new ArrayList<>();
        objectMap.forEach((k, v) -> {
            if (v != null) {
                userList.add((User) v);
            }
        });
        return userList;
    }
}
