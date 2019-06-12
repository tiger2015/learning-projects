package com.tiger.springboot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tiger.springboot.dao.LoginLogDao;
import com.tiger.springboot.entity.LoginLog;
import com.tiger.springboot.entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginLogService {
    private static int pageSize = 10;

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
    public PageResult<LoginLog> listLoginLog(int pageNumber) {
        Page<Object> page = PageHelper.startPage(pageNumber, pageSize);
        List<LoginLog> list = loginLogDao.selectAllLoginLog();
        PageResult<LoginLog> result = new PageResult<>();
        result.setPageCount(page.getPages());
        result.setPageSize(page.getPageSize());
        result.setPageNumber(page.getPageNum());
        result.setResult(list);
        return result;
    }

    @Override
    public PageResult<LoginLog> listLoginLog(String userName, int pageNumber) {
        Page<Object> page = PageHelper.startPage(pageNumber, pageSize);
        List<LoginLog> loginLogs = loginLogDao.selectLoginLogByUserName(userName);
        PageResult<LoginLog> result = new PageResult<>();
        result.setPageCount(page.getPages());
        result.setPageSize(page.getPageSize());
        result.setPageNumber(page.getPageNum());
        result.setResult(loginLogs);
        return result;
    }

    @Override
    public int removeLoginLog(List<Long> ids) {
        return loginLogDao.deleteLoginLog(ids);
    }
}
