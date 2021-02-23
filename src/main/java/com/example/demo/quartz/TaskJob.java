package com.example.demo.quartz;

import com.example.demo.config.TaskInfo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TaskJob implements Job {
    Logger logger = LoggerFactory.getLogger(TaskJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        String fileName = generateRandomName();
        String prefix = context.getJobDetail().getKey().getName();
        try(PrintWriter out = new PrintWriter(
                "c:\\tmp\\" + prefix + "-" + fileName + ".txt")) {
            out.println(context.getJobDetail().getKey().getName());
            TaskInfo taskInfo = (TaskInfo) context
                    .getJobDetail()
                    .getJobDataMap()
                    .get(TaskInfo.class.getSimpleName());
            out.println(taskInfo.getJobInformation());
        } catch(FileNotFoundException e) {
            logger.error("error writing a file");
        }

    }

    private String generateRandomName() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            int alphabetIndex = (int)(Math.random()*100)%26;
            int index = 'a' + alphabetIndex;
            sb.append((char)index);
        }
        return sb.toString();
    }
}
