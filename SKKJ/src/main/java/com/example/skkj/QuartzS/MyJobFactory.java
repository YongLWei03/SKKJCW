package com.example.skkj.QuartzS;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
 /**
      * @Author ZhouNan
      * @Description 怎么让Spring自动识别初始化Quartz定时任务实例呢？
      * 这就需要引用Spring管理的Bean，向Spring容器暴露所必须的bean，
      * 通过定义Job Factory实现自动注入。
      * @params
      * @Date 2018/6/12 0012  11:57
      */
public class MyJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
    @Autowired

    private AutowireCapableBeanFactory beanFactory;

    /**

     * 这里覆盖了super的createJobInstance方法，对其创建出来的类再进行autowire。

     */

    @Override

    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beanFactory = applicationContext.getAutowireCapableBeanFactory();
    }
}
