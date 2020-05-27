package com.hk.hkhttpclient.quartzs.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author : muwei
 * @ClassName:CronJob
 * @Date: 2020/5/25 09:08
 * @Description: TODO
 */
@Slf4j
public class CronJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("=========================定时任务每5秒执行一次===============================");
        log.info("jobName=====:"+jobExecutionContext.getJobDetail().getKey().getName());
        log.info("jobGroup=====:"+jobExecutionContext.getJobDetail().getKey().getGroup());
        log.info("taskData=====:"+jobExecutionContext.getJobDetail().getJobDataMap().get("taskData"));
    }
}
