package com.tiger.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

public class QuartzTest {

    public static void main(String[] args) throws SchedulerException {

        Properties props = new Properties();
        props.put("org.quartz.scheduler.instanceName", "Test");
        props.put("org.quartz.threadPool.threadCount", 2);
        SchedulerFactory schedulterFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulterFactory.getScheduler();
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job", "group").build();

        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger", "group1").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(10).repeatForever()).build();


        scheduler.scheduleJob(jobDetail, trigger1);
    }

}
