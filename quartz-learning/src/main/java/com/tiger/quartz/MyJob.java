package com.tiger.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * job不能内部类，匿名内部类
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("hello");

    }
}