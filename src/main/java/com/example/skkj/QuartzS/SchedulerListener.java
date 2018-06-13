package com.example.skkj.QuartzS;

import com.example.skkj.QuartzS.MyScheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    public MyScheduler myScheduler;

    //Quartz中的job自动注入spring容器托管的对象    
    @Bean
    public MyJobFactory autoWiringSpringBeanJobFactory() {
        return new MyJobFactory();
 }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            myScheduler.schedulerJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //
        schedulerFactoryBean.setJobFactory(autoWiringSpringBeanJobFactory());
        return schedulerFactoryBean;
    }

}