package com.tiger.springboot.controller;

import com.tiger.springboot.entity.ConsumerType;
import com.tiger.springboot.entity.Result;
import com.tiger.springboot.entity.ResultEnum;
import com.tiger.springboot.service.ConsumerTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/consumerType")
@Slf4j
public class ConsumerTypeController {
    @Autowired
    private ConsumerTypeService consumerTypeService;

    @RequestMapping(value="/list", method = {RequestMethod.GET})
    @ResponseBody
    public Object listAll(){
        log.info("request consumer type");
        List<ConsumerType> list = consumerTypeService.listAll();
        return new Result<>(ResultEnum.SUCCESS,list);
    }

}
