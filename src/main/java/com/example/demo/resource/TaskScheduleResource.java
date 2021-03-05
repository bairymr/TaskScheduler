package com.example.demo.resource;

public class TaskScheduleResource {

    private Integer intervalInSeconds;
    private Integer intervalInMinutes;
    private Integer intervalInHours;

    public Integer getIntervalInSeconds() {
        return intervalInSeconds;
    }

    public void setIntervalInSeconds(Integer intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

    public Integer getIntervalInMinutes() {
        return intervalInMinutes;
    }

    public void setIntervalInMinutes(Integer intervalInMinutes) {
        this.intervalInMinutes = intervalInMinutes;
    }

    public Integer getIntervalInHours() {
        return intervalInHours;
    }

    public void setIntervalInHours(Integer intervalInHours) {
        this.intervalInHours = intervalInHours;
    }
}
