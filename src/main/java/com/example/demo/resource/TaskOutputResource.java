package com.example.demo.resource;

import java.util.Date;

public class TaskOutputResource {
    private String message;
    private Date jobDate;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getJobDate() {
        return jobDate;
    }

    public void setJobDate(Date jobDate) {
        this.jobDate = jobDate;
    }
}
