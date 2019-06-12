package com.tiger.springboot.controller;

import com.tiger.springboot.entity.*;
import com.tiger.springboot.service.ConsumerRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class ConsumerRecordController {

    @Autowired
    private ConsumerRecordService consumerRecordService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    public Object add(@RequestBody ConsumerRecord consumerRecord) {  // 采用json格式发送数据
        boolean add = consumerRecordService.add(consumerRecord);
        return new Result<>(ResultEnum.SUCCESS, add);
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET}, consumes = {"application/json"})
    @ResponseBody
    public Object delete(@RequestBody IdList ids) {
        int count = consumerRecordService.delete(ids.getIds());
        return new Result<>(ResultEnum.SUCCESS, count);
    }

    @RequestMapping(value = "/list/{pageNumber}")
    @ResponseBody
    public Object listAll(@PathVariable int pageNumber) {
        PageResult<ConsumerRecord> result = consumerRecordService.listAll(pageNumber);
        return new Result<>(ResultEnum.SUCCESS, result);
    }

    @RequestMapping(value = "/list/{user}/{pageNumber}")
    @ResponseBody
    public Object listAll(@PathVariable String user, @PathVariable int pageNumber) {
        PageResult<ConsumerRecord> result = consumerRecordService.listAllByUser(user, pageNumber);
        return new Result<>(ResultEnum.SUCCESS, result);
    }

    @RequestMapping(value = "/listByType/{type}/{pageNumber}")
    @ResponseBody
    public Object listAllByType(@PathVariable String type, @PathVariable int pageNumber) {
        PageResult<ConsumerRecord> result = consumerRecordService.listAllByType(type, pageNumber);
        return new Result<>(ResultEnum.SUCCESS, result);
    }

    @RequestMapping(value = "/listByDate/{pageNumber}",method = {RequestMethod.POST},consumes = {"application/json"})
    @ResponseBody
    public Object listAllByDate(@PathVariable int pageNumber, @RequestBody DateParam params) {
        PageResult<ConsumerRecord> result = consumerRecordService.listAllByDate(params.getStart(), params.getEnd(), pageNumber);
        return new Result<>(ResultEnum.SUCCESS, result);
    }
}
