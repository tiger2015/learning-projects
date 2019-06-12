package com.tiger.springboot.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tiger.springboot.dao.ConsumerRecordDao;
import com.tiger.springboot.entity.ConsumerRecord;
import com.tiger.springboot.entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumerRecordServiceImpl implements ConsumerRecordService {
    public static int pageSize = 10;

    @Autowired
    private ConsumerRecordDao consumerRecordDao;

    @Override
    public boolean add(ConsumerRecord record) {
        return consumerRecordDao.insert(record);
    }

    @Override
    public int delete(List<Long> ids) {
        return consumerRecordDao.delete(ids);
    }

    @Override
    public PageResult<ConsumerRecord> listAll(int pageNumber) {
        Page<Object> page = PageHelper.startPage(pageNumber, pageSize);
        List<ConsumerRecord> records = consumerRecordDao.selectAll();
        PageResult<ConsumerRecord> result = new PageResult<>();
        result.setResult(records);
        result.setPageCount(page.getPages());
        result.setPageNumber(page.getPageNum());
        result.setPageSize(page.getPageSize());
        return result;
    }

    @Override
    public PageResult<ConsumerRecord> listAllByUser(String user, int pageNumber) {
        Page<Object> page = PageHelper.startPage(pageNumber, pageSize);
        List<ConsumerRecord> records = consumerRecordDao.selectByUser(user);
        PageResult<ConsumerRecord> result = new PageResult<>();
        result.setResult(records);
        result.setPageCount(page.getPages());
        result.setPageNumber(page.getPageNum());
        result.setPageSize(page.getPageSize());
        return result;
    }

    @Override
    public PageResult<ConsumerRecord> listAllByDate(Date start, Date end, int pageNumber) {
        Page<Object> page = PageHelper.startPage(pageNumber, pageSize);
        List<ConsumerRecord> records = consumerRecordDao.selectByDate(start, end);
        PageResult<ConsumerRecord> result = new PageResult<>();
        result.setResult(records);
        result.setPageCount(page.getPages());
        result.setPageNumber(page.getPageNum());
        result.setPageSize(page.getPageSize());
        return result;
    }

    @Override
    public PageResult<ConsumerRecord> listAllByType(String type, int pageNumber) {
        Page<Object> page = PageHelper.startPage(pageNumber, pageSize);
        List<ConsumerRecord> records = consumerRecordDao.selectByType(type);
        PageResult<ConsumerRecord> result = new PageResult<>();
        result.setResult(records);
        result.setPageCount(page.getPages());
        result.setPageNumber(page.getPageNum());
        result.setPageSize(page.getPageSize());
        return result;
    }

    @Override
    public PageResult<ConsumerRecord> listAllByUserAndDate(String user, Date start, Date end, int pageNumber) {
        Page<Object> page = PageHelper.startPage(pageNumber, pageSize);
        List<ConsumerRecord> records = consumerRecordDao.selectByUserAndDate(user, start, end);
        PageResult<ConsumerRecord> result = new PageResult<>();
        result.setResult(records);
        result.setPageCount(page.getPages());
        result.setPageNumber(page.getPageNum());
        result.setPageSize(page.getPageSize());
        return result;
    }

    @Override
    public PageResult<ConsumerRecord> listAllByUserAndType(String user, String type, int pageNumber) {
        Page<Object> page = PageHelper.startPage(pageNumber, pageSize);
        List<ConsumerRecord> records = consumerRecordDao.selectByUserAndType(user, type);
        PageResult<ConsumerRecord> result = new PageResult<>();
        result.setResult(records);
        result.setPageCount(page.getPages());
        result.setPageNumber(page.getPageNum());
        result.setPageSize(page.getPageSize());
        return result;
    }
}
