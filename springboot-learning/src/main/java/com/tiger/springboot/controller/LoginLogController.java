package com.tiger.springboot.controller;

import com.tiger.springboot.entity.LoginLog;
import com.tiger.springboot.entity.Result;
import com.tiger.springboot.entity.ResultEnum;
import com.tiger.springboot.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loginLog")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public Object loginHistory(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate) {
        List<LoginLog> logs = loginLogService.listLoginLog(startDate, endDate);
        return new Result<>(ResultEnum.SUCCESS, logs);
    }


    @RequestMapping(value = "/list/{userName}", method = {RequestMethod.GET})
    @ResponseBody
    public Object userLoginLog(@PathVariable String userName){
        List<LoginLog> logs = loginLogService.listUserLoginLog(userName);
        System.out.println("============="+logs.get(0).getLoginTime());

        return new Result<>(ResultEnum.SUCCESS, logs);
    }
}
