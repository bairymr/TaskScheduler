package com.example.demo.assembler;

import com.example.demo.config.TaskInfo;
import com.example.demo.config.TaskSchedule;
import com.example.demo.resource.TaskInputResource;
import org.springframework.stereotype.Component;

@Component
public class TaskAssembler {

    public TaskInfo toTaskInfo(TaskInputResource taskInputResource) {
        if (taskInputResource != null
                && taskInputResource.getJobName() != null
                && taskInputResource.getTaskScheduleResource() != null
               && isTaskScheduleIsCorrect(taskInputResource)) {
            TaskInfo taskInfo = new TaskInfo();
            taskInfo.setJobName(taskInputResource.getJobName());
            taskInfo.setJobInformation(taskInputResource.getJobInformation());
            TaskSchedule taskSchedule = new TaskSchedule();
            taskSchedule.setIntervalInSeconds(taskInputResource.getTaskScheduleResource().getIntervalInSeconds());
            taskSchedule.setIntervalInMinutes(taskInputResource.getTaskScheduleResource().getIntervalInMinutes());
            taskSchedule.setIntervalInHours(taskInputResource.getTaskScheduleResource().getIntervalInHours());
            taskInfo.setTaskSchedule(taskSchedule);
            return taskInfo;
        } else {
            return null;
        }
    }

    private boolean isTaskScheduleIsCorrect(TaskInputResource taskInputResource) {
        if (taskInputResource == null || taskInputResource.getTaskScheduleResource() == null) {
            return false;
        }
        if (taskInputResource.getTaskScheduleResource().getIntervalInHours() == null
            && taskInputResource.getTaskScheduleResource().getIntervalInMinutes() == null
            && taskInputResource.getTaskScheduleResource().getIntervalInSeconds() == null) {
            return false;
        }
        return true;
    }
}
