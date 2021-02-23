package com.example.demo.service;

import com.example.demo.config.TaskInfo;
import com.example.demo.quartz.TaskJob;
import com.example.demo.resource.TaskOutputResource;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Service
public class TaskService {

    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    Scheduler scheduler;

    public TaskOutputResource scheduleTask(TaskInfo taskInfo) throws SchedulerException {
        Date jobDate = scheduler.scheduleJob(
                TaskInfoUtil.buildJobDetail(TaskJob.class, taskInfo),
                TaskInfoUtil.buildTrigger(taskInfo));

        TaskOutputResource taskOutputResource = new TaskOutputResource();
        taskOutputResource.setJobDate(jobDate);
        return taskOutputResource;
    }

    public void triggerTask(TaskInfo taskInfo) throws SchedulerException {

        JobKey jobKey = new JobKey(taskInfo.getJobName());

        scheduler.triggerJob(jobKey);
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch(SchedulerException e) {
            logger.error("couldn't start scheduler");
        }
    }

    @PreDestroy
    public void shutDown() {
        try {
            scheduler.shutdown();
        } catch(SchedulerException e) {
            logger.error("couldn't shutdown scheduler");
        }
    }
}
