package com.tiger.springboot.service;

import com.tiger.springboot.dao.LoginLogDao;
import com.tiger.springboot.entity.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public boolean addLoginLog(LoginLog loginLog) {
        return loginLogDao.insert(loginLog);
    }
}
