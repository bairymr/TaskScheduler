package com.example.demo.resource;

public class TaskInputResource {

    private String jobName;
    private String jobInformation;
    private TaskScheduleResource taskScheduleResource;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobInformation() {
        return jobInformation;
    }

    public void setJobInformation(String jobInformation) {
        this.jobInformation = jobInformation;
    }

    public TaskScheduleResource getTaskScheduleResource() {
        return taskScheduleResource;
    }

    public void setTaskScheduleResource(TaskScheduleResource taskScheduleResource) {
        this.taskScheduleResource = taskScheduleResource;
    }
}
