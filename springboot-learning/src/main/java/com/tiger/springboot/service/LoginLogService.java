package com.tiger.springboot.service;

import com.tiger.springboot.entity.LoginLog;

import java.util.List;

public interface LoginLogService {
    boolean addLoginLog(LoginLog loginLog);

    List<LoginLog> listLoginLog(String startDate, String endDate);

    List<LoginLog> listUserLoginLog(String userName);
}
