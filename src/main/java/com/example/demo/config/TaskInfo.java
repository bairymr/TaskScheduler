package com.example.demo.config;

public class TaskInfo {
    private String jobName;
    private String callBackDate;
    private String jobInformation;
    private TaskSchedule taskSchedule;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCallBackDate() {
        return callBackDate;
    }

    public void setCallBackDate(String callBackDate) {
        this.callBackDate = callBackDate;
    }

    public String getJobInformation() {
        return jobInformation;
    }

    public void setJobInformation(String jobInformation) {
        this.jobInformation = jobInformation;
    }

    public TaskSchedule getTaskSchedule() {
        return taskSchedule;
    }

    public void setTaskSchedule(TaskSchedule taskSchedule) {
        this.taskSchedule = taskSchedule;
    }
}
