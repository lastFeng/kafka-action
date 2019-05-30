/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springboot.springquartz.config;

import com.springboot.springquartz.task.ScheduleTask;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/5/17 11:00
 */
@Configuration
public class QuartzConfig {

    /**
     * 配置定时任务
     * @param task 需要执行的任务
     * */

    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTask task){
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();

        /**
         * 是否并发执行
         * 例如每5s执行一次任务，但是当前任务没有执行完成，就已经过了5s了
         * 如果为true，则进行并发执行，如果为false则依次执行，等待上一个任务执行完成后在执行
         * */
        jobDetailFactoryBean.setConcurrent(false);
        jobDetailFactoryBean.setName("srd-chhliu");
        jobDetailFactoryBean.setGroup("srd");
        /**需要执行的实体类对应的对象*/
        jobDetailFactoryBean.setTargetObject(task);
        /**sayHello为需要执行的方法*/
        jobDetailFactoryBean.setTargetMethod("sayHello");
        return jobDetailFactoryBean;
    }

    /**
     * 配置定时任务的触发器，也就是什么时候出发执行定时任务
     * */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronTrigger(MethodInvokingJobDetailFactoryBean jobDetail){
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();

        trigger.setJobDetail(jobDetail.getObject());
        trigger.setCronExpression("0 30 20 * * ?");
        trigger.setName("srd-chhliu");
        return trigger;
    }

    /**
     * 定义quartz调度工厂
     * */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();

        // 用于quartz集群，QuartzScheduler启动时更新已存在的Job
        bean.setOverwriteExistingJobs(true);

        // 延时启动，应用启动1s后
        bean.setStartupDelay(1);

        // 注册触发器
        bean.setTriggers(cronJobTrigger);
        return bean;
    }
}