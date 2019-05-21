package com.tiger.springboot.service;

import com.tiger.springboot.dao.LoginLogDao;
import com.tiger.springboot.entity.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;


    @Override
    public boolean addLoginLog(LoginLog loginLog) {
        return loginLogDao.insert(loginLog);
    }

    @Override
    public List<LoginLog> listLoginLog(String startDate, String endDate) {
        return loginLogDao.selectLoginLogByTime(startDate, endDate);
    }

    @Cacheable(key = "#root.args[0]", cacheNames = {"loginLogCache"})
    @Override
    public List<LoginLog> listUserLoginLog(String userName) {
        return loginLogDao.selectLoginLogByUserName(userName);
    }
}
