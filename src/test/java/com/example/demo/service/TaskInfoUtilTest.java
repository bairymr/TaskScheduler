package com.example.demo.service;

import com.example.demo.quartz.TaskJob;
import org.junit.jupiter.api.Test;
import org.quartz.JobDetail;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class TaskInfoUtilTest {

    @Test
    public void whenTaskInfoIsNullJobDetailIsNull() {
        JobDetail jobDetail = TaskInfoUtil.buildJobDetail(
                TaskJob.class,
                null);
        assertTrue("job detail is null",
                 jobDetail == null);
    }

}
