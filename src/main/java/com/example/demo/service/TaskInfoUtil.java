package com.example.demo.service;

import com.example.demo.config.TaskInfo;
import org.quartz.*;

public class TaskInfoUtil {

    private TaskInfoUtil() {

    }

    public static JobDetail buildJobDetail(final Class jobClass,
                                           final TaskInfo taskInfo) {

        if (taskInfo == null) {
            return null; //without task info there is no job data.
        }
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

        if (taskInfo == null) {
            return null;
        }
        SimpleScheduleBuilder builder =
                SimpleScheduleBuilder
                .simpleSchedule()
                .withRepeatCount(100);

        if (taskInfo.getTaskSchedule() != null) {
            if (taskInfo.getTaskSchedule().getIntervalInSeconds() != null) {
                builder.withIntervalInSeconds(taskInfo.getTaskSchedule().getIntervalInSeconds());
            } else if (taskInfo.getTaskSchedule().getIntervalInMinutes() != null) {
                builder.withIntervalInMinutes(taskInfo.getTaskSchedule().getIntervalInMinutes());
            } else {
                builder.withIntervalInHours(taskInfo.getTaskSchedule().getIntervalInHours());
            }
        }
        return      TriggerBuilder
                   .newTrigger()
                   .withIdentity(taskInfo.getJobName())
                   .withSchedule(builder)
                   .startNow()
                   .build();

    }
}
