package com.example.demo.service;

import com.example.demo.config.TaskInfo;
import org.quartz.*;

public class TaskInfoUtil {

    private TaskInfoUtil() {

    }

    public static JobDetail buildJobDetail(final Class jobClass,
                                           final TaskInfo taskInfo) {

        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(),
                       taskInfo);

        return JobBuilder
                .newJob(jobClass)
                .withIdentity(taskInfo.getJobName())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final TaskInfo taskInfo) {
        SimpleScheduleBuilder builder =
                SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(taskInfo.getIntervalInSeconds())
                .withRepeatCount(100);

        return      TriggerBuilder
                   .newTrigger()
                   .withIdentity(taskInfo.getJobName())
                   .withSchedule(builder)
                   .startNow()
                   .build();

    }
}
