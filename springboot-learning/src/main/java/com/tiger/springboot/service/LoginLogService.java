package com.tiger.springboot.service;

import com.tiger.springboot.entity.LoginLog;
import com.tiger.springboot.entity.PageResult;

import java.util.List;

public interface LoginLogService {
    boolean addLoginLog(LoginLog loginLog);

    List<LoginLog> listLoginLog(String startDate, String endDate);

    PageResult<LoginLog> listLoginLog(int pageNumber);

    PageResult<LoginLog> listLoginLog(String userName, int pageNumber);

    int removeLoginLog(List<Long> ids);
}
