package com.tiger.springboot.controller;

import com.tiger.springboot.entity.Result;
import com.tiger.springboot.entity.ResultEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public Result<String> defaultExceptionHandler(HttpServletRequest request, Exception e) {
        return new Result<>(ResultEnum.SERVER_ERROR, e.getMessage());
    }
}
