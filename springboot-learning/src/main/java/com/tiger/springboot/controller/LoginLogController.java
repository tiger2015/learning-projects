package com.tiger.springboot.controller;

import com.tiger.springboot.entity.*;
import com.tiger.springboot.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loginLog")
@Slf4j
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public Object loginHistory(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate) {
        List<LoginLog> logs = loginLogService.listLoginLog(startDate, endDate);
        return new Result<>(ResultEnum.SUCCESS, logs);
    }


    @RequestMapping(value = "/list/{pageNumber}", method = {RequestMethod.GET})
    @ResponseBody
    public Object listLoginLog(@PathVariable int pageNumber) {
        PageResult<LoginLog> result = loginLogService.listLoginLog(pageNumber);
        return new Result<>(ResultEnum.SUCCESS, result);
    }

    @RequestMapping(value = "/list/{userName}/{pageNumber}", method = {RequestMethod.GET})
    @ResponseBody
    public Object listLoginLog(@PathVariable String userName, @PathVariable int pageNumber) {
        PageResult<LoginLog> result = loginLogService.listLoginLog(userName, pageNumber);
        return new Result<>(ResultEnum.SUCCESS, result);
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST}, consumes = {"application/json"})
    public Object deleteLoginLog(@RequestBody IdList ids) {
        log.info(ids.getIds().toString());
        int count = loginLogService.removeLoginLog(ids.getIds());
        return new Result<>(ResultEnum.SUCCESS, count);
    }
}
