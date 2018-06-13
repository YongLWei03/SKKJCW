package com.example.skkj.QuartzS;

import com.example.skkj.timing.CommandInformationTime;
import com.example.skkj.timing.EquipmentState;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler extends AdaptableJobFactory {


    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    public void schedulerJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        startScheduler1(scheduler);
        startScheduler2(scheduler);
    }

    public void startScheduler1(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(CommandInformationTime.class).withIdentity("job1", "jobGroup1").build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 * * ?");
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void startScheduler2(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(EquipmentState.class).withIdentity("job2", "jobGroup2").build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 10 * * ?");
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "triggerGroup2")
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);

    }
}
