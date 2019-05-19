package com.tiger.springboot.dao;

import com.tiger.springboot.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginLogDao {
    /**
     * 插入一条登录记录
     * @param loginLog
     * @return
     */
    boolean insert(LoginLog loginLog);
}
