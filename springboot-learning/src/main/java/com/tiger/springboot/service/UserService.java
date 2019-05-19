package com.tiger.springboot.service;

import com.tiger.springboot.entity.User;

public interface UserService {
    boolean add(User user);
    User findUser(String userName);
}
