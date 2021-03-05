package com.example.demo.controller;

import com.example.demo.config.TaskInfo;
import com.example.demo.resource.TaskInputResource;
import com.example.demo.resource.TaskOutputResource;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.quartz.SchedulerException;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class TaskControllerTest {

    @InjectMocks
    TaskController taskController;

    @Mock
    TaskService taskService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenServiceSuccessfulThenTaskOutputResourceIsNotNull()
            throws SchedulerException {


        Mockito.when(taskService.scheduleTask(any(TaskInfo.class)))
                .thenReturn(new TaskOutputResource());

        TaskInputResource taskInputResource = new TaskInputResource();
        TaskOutputResource taskOutputResource = taskController.scheduleTask(taskInputResource).getBody();
        assertNotNull("Task Output resource", taskOutputResource);

    }

    @Test
    public void whenServiceFailsThenTaskOutputResourceHasErrorMessage()
            throws SchedulerException {


        Mockito.when(taskService.scheduleTask(any(TaskInfo.class)))
                .thenThrow(SchedulerException.class);

        TaskInputResource taskInputResource = new TaskInputResource();
        TaskOutputResource taskOutputResource = taskController.scheduleTask(taskInputResource).getBody();
        assertTrue("exception message",
                taskOutputResource.getMessage().startsWith("Job couldn't be scheduled"));

    }

}
