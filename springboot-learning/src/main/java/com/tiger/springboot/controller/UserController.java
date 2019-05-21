package com.tiger.springboot.controller;

import com.tiger.springboot.common.CommonUtil;
import com.tiger.springboot.entity.LoginLog;
import com.tiger.springboot.entity.Result;
import com.tiger.springboot.entity.ResultEnum;
import com.tiger.springboot.entity.User;
import com.tiger.springboot.service.LoginLogService;
import com.tiger.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public Object login(HttpServletRequest request, @RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {
        User user = userService.findUser(userName);
        if (user == null || !userName.equals(user.getUserName()) || !user.getPassword().equals(CommonUtil.md5Encode(password, "utf-8"))) {
            return new Result<>(ResultEnum.USER_ERROR);
        }
        String remoteAddr = request.getRemoteAddr();
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginIp(remoteAddr);
        loginLog.setUserName(userName);
        loginLog.setUrl(request.getServletPath());
        loginLogService.addLoginLog(loginLog);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @ResponseBody
    public Object register(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, String email, String phoneNumber) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        userService.add(user);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public Result<String> keyIsUniqueExceptionHandler(HttpServletRequest request, Exception e) {
        return new Result<>(ResultEnum.USER_EXISTS);
    }
}
