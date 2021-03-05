package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class TaskServiceTest {

    @InjectMocks
    TaskService taskService;

    @Mock
    Scheduler scheduler;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenTaskDataIsNullThenSchedulerWillThrowException()
            throws SchedulerException {

            Mockito.when(scheduler
                    .scheduleJob(null, null))
                    .thenThrow(SchedulerException.class);

        Assertions.assertThrows(SchedulerException.class,
                () -> {
                    taskService.scheduleTask(null);
                });

    }


}
