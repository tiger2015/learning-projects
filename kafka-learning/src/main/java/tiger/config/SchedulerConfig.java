package tiger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import tiger.Task;
import tiger.job.UserProducerJob;

import java.util.Properties;

@Configuration
public class SchedulerConfig {
    // 调度器
    @Bean(destroyMethod = "destroy")
    public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        Properties props = new Properties();
        props.setProperty("org.quartz.scheduler.instanceName", "kafka-task");
        props.setProperty("org.quartz.threadPool.threadCount", "3");
        props.setProperty("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
        props.setProperty("org.quartz.scheduler.threadName", "send-message");

        factoryBean.setQuartzProperties(props);
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setTriggers(triggerFactoryBean().getObject());

        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

    // tigger工厂方法
    @Bean
    public SimpleTriggerFactoryBean triggerFactoryBean() {
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setGroup("group");
        simpleTriggerFactoryBean.setName("send-message");
        simpleTriggerFactoryBean.setRepeatInterval(200L);
        simpleTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        simpleTriggerFactoryBean.afterPropertiesSet();
        return simpleTriggerFactoryBean;
    }

    // jobdetail 工厂方法
    @Bean
    public MethodInvokingJobDetailFactoryBean jobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactoryBean.setGroup("group");
        jobDetailFactoryBean.setName("send-message");
        jobDetailFactoryBean.setTargetObject(userProducerJob());
        jobDetailFactoryBean.setTargetMethod("sendMessage");
        jobDetailFactoryBean.setConcurrent(false);
        return jobDetailFactoryBean;
    }

    @Bean
    public UserProducerJob userProducerJob() {
        return new UserProducerJob();
    }
}
