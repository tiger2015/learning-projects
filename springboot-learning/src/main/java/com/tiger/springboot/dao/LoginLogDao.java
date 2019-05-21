package com.tiger.springboot.dao;

import com.tiger.springboot.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginLogDao {
    /**
     * 插入一条登录记录
     *
     * @param loginLog
     * @return
     */
    boolean insert(LoginLog loginLog);

    /**
     * 查询一段时间的用户登录记录
     *
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return
     */
    List<LoginLog> selectLoginLogByTime(String startDate, String endDate);

    /**
     * 查询某个用户的登录记录
     * @param userName
     * @return
     */
    List<LoginLog> selectLoginLogByUserName(String userName);
}
