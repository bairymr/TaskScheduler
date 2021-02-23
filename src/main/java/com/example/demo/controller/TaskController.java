package com.example.demo.controller;

import com.example.demo.config.TaskInfo;
import com.example.demo.resource.TaskInputResource;
import com.example.demo.resource.TaskOutputResource;
import com.example.demo.service.TaskService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task/v1/")
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(method = RequestMethod.POST,
      value = "/schedule",
      consumes = "application/json",
      produces = "application/json")
    public ResponseEntity<TaskOutputResource> scheduleTask(
            @RequestBody TaskInputResource taskInputResource) {

        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setJobName(taskInputResource.getJobName());
        taskInfo.setJobInformation(taskInputResource.getJobInformation());
        taskInfo.setIntervalInSeconds(taskInputResource.getRepeatInterval());

        TaskOutputResource taskOutputResource = new TaskOutputResource();
        try {
            taskOutputResource = taskService.scheduleTask(taskInfo);
        } catch (SchedulerException e) {
            taskOutputResource.setMessage("Job couldn't be scheduled. error msg: "
                 + e.getMessage());
        }
        return new ResponseEntity<>(taskOutputResource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            value="/trigger",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<TaskOutputResource> resumeTask(
            @RequestBody TaskInputResource taskInputResource) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setJobName(taskInputResource.getJobName());
        TaskOutputResource taskOutputResource = new TaskOutputResource();
        taskOutputResource.setMessage("Job " + taskInputResource.getJobName() + " triggered.");
        try {
            taskService.triggerTask(taskInfo);
        } catch (SchedulerException e) {
            taskOutputResource.setMessage("Job coudnt be scheduled. Message is "
            + e.getMessage());
            taskOutputResource.setJobDate(null);
        }

        return new ResponseEntity<>(taskOutputResource, HttpStatus.OK);
    }
}
