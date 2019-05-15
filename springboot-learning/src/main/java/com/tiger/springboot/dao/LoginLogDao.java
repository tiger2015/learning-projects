package com.tiger.springboot.dao;

import com.tiger.springboot.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogDao {
    boolean insert(LoginLog loginLog);
}
